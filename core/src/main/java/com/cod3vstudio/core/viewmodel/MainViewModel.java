package com.cod3vstudio.core.viewmodel;

import android.content.Context;
import android.content.SharedPreferences;

import com.cod3vstudio.core.R;
import com.cod3vstudio.core.model.entities.User;
import com.cod3vstudio.core.model.responses.APIResponse;
import com.cod3vstudio.core.model.services.clouds.ServiceComponent;
import com.cod3vstudio.core.model.services.storages.ModelComponent;
import com.cod3vstudio.core.util.Configuration;
import com.cod3vstudio.core.view.INavigator;

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

    //endregion

    //region Constructors

    public MainViewModel(INavigator navigator, ModelComponent modelComponent, ServiceComponent serviceComponent) {
        super(navigator);
        mModelComponent = modelComponent;
        mServiceComponent = serviceComponent;
    }

    //endregion

    //region Getters and Setters

    @Override
    public INavigator getNavigator() {
        return super.getNavigator();
    }

    //endregion

    //region Lifecycle

    @Override
    public void onCreate() {
        super.onCreate();
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
    }

    //endregion

    //region Public methods

    public boolean isSignedUserAvailable() {
        return getNavigator().getApplication().isSignedUserAvailable();
    }

    public void signOutCommand () {
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
}
