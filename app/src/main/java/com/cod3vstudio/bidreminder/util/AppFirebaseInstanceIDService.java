package com.cod3vstudio.bidreminder.util;

import android.content.SharedPreferences;
import android.util.Log;

import com.cod3vstudio.bidreminder.App;
import com.cod3vstudio.core.model.entities.User;
import com.cod3vstudio.core.model.responses.APIResponse;
import com.cod3vstudio.core.model.services.clouds.ServiceComponent;
import com.cod3vstudio.core.util.Configuration;
import com.cod3vstudio.core.view.BaseApplication;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 10/12/2016.
 */
public class AppFirebaseInstanceIDService extends FirebaseInstanceIdService {

    private static final String TAG = "FIREBASE";

    @Inject
    ServiceComponent mServiceComponent;

    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
        App.sharedComponent().inject(this);
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Refreshed token: " + refreshedToken);
        sendRegistrationToServer(refreshedToken);
    }

    private void sendRegistrationToServer(String token) {
        BaseApplication application = (BaseApplication) getApplication();
        if (application.isSignedUserAvailable()) {
            mServiceComponent.getUserService().updateFirebaseToken(application.getSignedInUser().getId(), token).enqueue(new Callback<APIResponse<User>>() {
                @Override
                public void onResponse(Call<APIResponse<User>> call, Response<APIResponse<User>> response) {
                    if (response.isSuccessful() && response.body() != null && response.body().isSuccess()) {
                        Log.d(TAG, response.body().getData().toString());
                    } else {
                        Log.d(TAG, "Update firebase token failed");
                    }
                }

                @Override
                public void onFailure(Call<APIResponse<User>> call, Throwable t) {
                    Log.d(TAG, "Update firebase token failed");
                }
            });
        } else {
            SharedPreferences sharedPreferences = getSharedPreferences(Configuration.APP_PREFS, MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(Configuration.FIREBASE_TOKEN_DEF, token);
            editor.apply();
            Log.d(TAG, "Save to local");
        }
    }
}
