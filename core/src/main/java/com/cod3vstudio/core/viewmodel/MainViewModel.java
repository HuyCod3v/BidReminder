package com.cod3vstudio.core.viewmodel;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.databinding.Bindable;
import android.util.Log;

import com.cod3vstudio.core.BR;
import com.cod3vstudio.core.R;
import com.cod3vstudio.core.model.entities.User;
import com.cod3vstudio.core.model.responses.APIResponse;
import com.cod3vstudio.core.model.services.clouds.ServiceComponent;
import com.cod3vstudio.core.model.services.storages.ModelComponent;
import com.cod3vstudio.core.util.Configuration;
import com.cod3vstudio.core.util.Constants;
import com.cod3vstudio.core.view.INavigator;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 7/26/2016.
 */

public class MainViewModel extends BaseViewModel {

    //region Properties

    private ModelComponent mModelComponent;
    private ServiceComponent mServiceComponent;

    private static final String TAG ="MainModel";

    private User mLoginUser;

    //endregion

    //region Constructors

    public MainViewModel(INavigator navigator, ModelComponent modelComponent, ServiceComponent serviceComponent) {
        super(navigator);
        mModelComponent = modelComponent;
        mServiceComponent = serviceComponent;
    }

    //endregion

    //region Getters and Setters

    @Bindable
    public User getLoginUser() {
        return mLoginUser;
    }

    public void setLoginUser(User loginUser) {
        mLoginUser = loginUser;
        notifyPropertyChanged(BR.loginUser);
    }

    @Override
    public INavigator getNavigator() {
        return super.getNavigator();
    }

    //endregion

    //region Lifecycle

    @Override
    public void onCreate() {
        super.onCreate();
        register();
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
        unregister();
    }

    //endregion

    //region Public methods

    public boolean isSignedUserAvailable() {
        return getNavigator().getApplication().isSignedUserAvailable();
    }


    public void signOutCommand () {
        new AlertDialog.Builder(getCurrentActivity())
                .setTitle(getCurrentActivity().getResources().getString(R.string.title_signout))
                .setMessage(getCurrentActivity().getResources().getString(R.string.confirm_signout))
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        resetFirebaseToken();
                        resetUserToken();
                        getNavigator().getApplication().setSignedInUser(null);
                        getNavigator().navigateTo(Constants.SIGN_IN_PAGE);
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    public void signInIfRemembered() {
        SharedPreferences sharedPreferences = getCurrentActivity().getSharedPreferences(Configuration.APP_PREFS, Context.MODE_PRIVATE);
        String token = sharedPreferences.getString(User.TOKEN, "");
        if (!token.equals("")) {
            mServiceComponent.getUserService().reSignIn(token).enqueue(new Callback<APIResponse<User>>() {
                @Override
                public void onResponse(Call<APIResponse<User>> call, Response<APIResponse<User>> response) {
                    if (response.isSuccessful() && response.body() != null && response.body().isSuccess()) {
                        getNavigator().getApplication().setSignedInUser(response.body().getData());
                        postSticky(response.body().getData());
                        showMessage(getCurrentActivity().getString(R.string.logged_in_successfully));
                    } else {
                        showMessage(getCurrentActivity().getString(R.string.incorrect_account));
                    }
                }

                @Override
                public void onFailure(Call<APIResponse<User>> call, Throwable t) {
                    showMessage(getCurrentActivity().getString(R.string.account_login_failures));
                }
            });
        }
    }

    //endregion

    //region Private methods

    public void resetUserToken(){
        SharedPreferences sharedPreferences = getCurrentActivity().getSharedPreferences(Configuration.APP_PREFS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(User.TOKEN, "");
        editor.apply();
    }

    private void resetFirebaseToken(){
        mServiceComponent.getUserService().updateFirebaseToken(getNavigator().getApplication().getSignedInUser().getId(),"").enqueue(new Callback<APIResponse<User>>() {
            @Override
            public void onResponse(Call<APIResponse<User>> call, Response<APIResponse<User>> response) {
                Log.d(TAG,"Reset Firebase Token");
            }

            @Override
            public void onFailure(Call<APIResponse<User>> call, Throwable t) {

            }
        });
    }


    //endregion

    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void event(User loginUser){
        setLoginUser(loginUser);
        unregister();
    }

}
