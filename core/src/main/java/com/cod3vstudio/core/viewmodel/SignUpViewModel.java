package com.cod3vstudio.core.viewmodel;

import android.app.Service;
import android.util.Patterns;

import com.cod3vstudio.core.R;
import com.cod3vstudio.core.model.entities.User;
import com.cod3vstudio.core.model.responses.APIResponse;
import com.cod3vstudio.core.model.services.clouds.ServiceComponent;
import com.cod3vstudio.core.model.services.storages.ModelComponent;
import com.cod3vstudio.core.view.INavigator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 8/3/2016.
 */
public class SignUpViewModel extends BaseViewModel {

    //region Properties

    private String mEmail;

    private String mPassword;

    private String mRetypePassword;

    private String mName;

    private ModelComponent mModelComponent;

    private ServiceComponent mServiceComponent;

    //endregion

    //region Getter and Setter

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        mPassword = password;
    }

    public String getRetypePassword() {
        return mRetypePassword;
    }

    public void setRetypePassword(String retypePassword) {
        mRetypePassword = retypePassword;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    //endregion

    //region Constructors

    public SignUpViewModel(INavigator navigator, ModelComponent modelComponent, ServiceComponent serviceComponent) {
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
        mRetypePassword = "";
        mName = "";
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
        mRetypePassword = null;
        mName = null;
    }

    //endregion

    //region Public Methods

    public boolean validateInput() {

        if (!Patterns.EMAIL_ADDRESS.matcher(mEmail).matches()) {
            showMessage("Email không hợp lệ");
            return false;
        }

        if (mPassword.trim().length() < 6 || mPassword.trim().length() > 50) {
            showMessage("Mật khẩu phải từ 6 đến 50 ký tự");
            return false;
        }

        if (!mPassword.trim().equals(mRetypePassword.trim())) {
            showMessage("Mật khẩu và mật khẩu nhập lại không trùng");
            return false;
        }

        if (mName.trim().length() < 6 || mName.trim().length() > 50) {
            showMessage("Tên hiển thị phải từ 6 đến 50 ký tự");
            return false;
        }

        return true;
    }

    public void signUpCommand() {
        if (validateInput()) {
            User user = initUser();
            getNavigator().showBusyIndicator(getCurrentActivity().getString(R.string.signing_up));

            mServiceComponent.getUserService().signUp(user.getEmail(), user.getPassword(), user.getName(), "...").enqueue(new Callback<APIResponse<Boolean>>() {
                @Override
                public void onResponse(Call<APIResponse<Boolean>> call, Response<APIResponse<Boolean>> response) {
                    if (response.isSuccessful() && response.body() != null && response.body().isSuccess()) {
                        getNavigator().goBack();
                        showMessage(getCurrentActivity().getString(R.string.account_registration_successful));
                    } else {
                        showMessage(getCurrentActivity().getString(R.string.account_registration_error));
                    }
                    getNavigator().hideBusyIndicator();
                }

                @Override
                public void onFailure(Call<APIResponse<Boolean>> call, Throwable t) {
                    showMessage(getCurrentActivity().getString(R.string.account_registration_error));
                    getNavigator().hideBusyIndicator();
                }
            });

        }
    }

    public void showSignInCommand() {
        getNavigator().goBack();
    }

    //endregion

    //region Private methods

    private User initUser() {
        User user = new User();
        user.setEmail(mEmail);
        user.setPassword(mPassword);
        user.setName(mName);
        return user;
    }

    private boolean hasResponse(Response<APIResponse> response) {
        if (response.isSuccessful() && response.body() != null && response.body().isSuccess()) {
            return true;
        } else {
            return false;
        }
    }

    //endregion
}
