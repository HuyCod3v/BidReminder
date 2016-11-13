package com.cod3vstudio.core.viewmodel;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.util.Patterns;

import com.cod3vstudio.core.R;
import com.cod3vstudio.core.model.entities.User;
import com.cod3vstudio.core.model.responses.APIResponse;
import com.cod3vstudio.core.model.services.clouds.ServiceComponent;
import com.cod3vstudio.core.model.services.storages.ModelComponent;
import com.cod3vstudio.core.util.Configuration;
import com.cod3vstudio.core.util.Constants;
import com.cod3vstudio.core.view.INavigator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 8/1/2016.
 */
public class SignInViewModel extends BaseViewModel {

    //region Properties

    private String mPassword;

    private String mEmail;

    private ModelComponent mModelComponent;
    private ServiceComponent mServiceComponent;

    //endregion

    //region Getter and Setter

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        mPassword = password;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    //endregion

    //region Constructors

    public SignInViewModel(INavigator navigator, ModelComponent modelComponent, ServiceComponent serviceComponent) {
        super(navigator);
        mModelComponent = modelComponent;
        mServiceComponent = serviceComponent;
    }

    //endregion

    //region Lifecycle

    @Override
    public void onCreate() {
        super.onCreate();

        mEmail = "";
        mPassword = "";
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        mEmail = null;
        mPassword = null;
    }

    //endregion

    //region Private Methods

    private boolean validateInput() {
        if (!Patterns.EMAIL_ADDRESS.matcher(mEmail).matches()) {
            showMessage("Email không hợp lệ");
            return false;
        }

        if (mPassword.trim().length() < 6 || mPassword.length() > 50) {
            showMessage("Mật khẩu phải từ 6 tới 50 ký tự");
            return false;
        }

        return true;
    }

    //endregion

    //region Public Methods

    public void showRegister() {
        getNavigator().navigateTo(Constants.SIGN_UP_PAGE);
    }

    public void signInCommand() {
        if (validateInput()) {
            getNavigator().showBusyIndicator(getCurrentActivity().getString(R.string.signing_in));

            mServiceComponent.getUserService().signIn(mEmail, mPassword).enqueue(new Callback<APIResponse<User>>() {
                @Override
                public void onResponse(Call<APIResponse<User>> call, Response<APIResponse<User>> response) {
                    if (response.isSuccessful() && response.body() != null && response.body().isSuccess()) {
                        getNavigator().goBack();
                        getNavigator().getApplication().setSignedInUser(response.body().getData());
                        cacheUserToken(response.body().getData().getRememberToken());
                        postSticky(response.body().getData());
                        showMessage(getCurrentActivity().getString(R.string.logged_in_successfully));
                    } else {
                        showMessage(getCurrentActivity().getString(R.string.incorrect_account));
                    }

                    getNavigator().hideBusyIndicator();

                    updateFirebaseToken();
                }

                @Override
                public void onFailure(Call<APIResponse<User>> call, Throwable t) {
                    getNavigator().hideBusyIndicator();
                    showMessage(getCurrentActivity().getString(R.string.account_login_failures));
                }
            });
        }
    }

    private void updateFirebaseToken() {
        SharedPreferences sharedPreferences = getCurrentActivity().getSharedPreferences(Configuration.APP_PREFS, Context.MODE_PRIVATE);
        String token = sharedPreferences.getString(Configuration.FIREBASE_TOKEN_DEF, "");

        if (token.equals("")) {
            return;
        }

        mServiceComponent.getUserService().updateFirebaseToken(getNavigator().getApplication().getSignedInUser().getId(), token).enqueue(new Callback<APIResponse<User>>() {
            @Override
            public void onResponse(Call<APIResponse<User>> call, Response<APIResponse<User>> response) {
                if (response.isSuccessful() && response.body() != null && response.body().isSuccess()) {
                    Log.d("Firebase", response.body().getData().toString());
                } else {
                    Log.d("Firebase", "Update firebase token failed");
                }
            }

            @Override
            public void onFailure(Call<APIResponse<User>> call, Throwable t) {
                Log.d("Firebase", "Update firebase token failed");
            }
        });
    }

    private void cacheUserToken(String token) {
        SharedPreferences sharedPreferences = getCurrentActivity().getSharedPreferences(Configuration.APP_PREFS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(User.TOKEN, token);
        editor.apply();
    }

    //endregion

    //region Private methods


    //endregion

}
