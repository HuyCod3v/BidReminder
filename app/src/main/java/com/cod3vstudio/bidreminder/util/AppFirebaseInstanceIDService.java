package com.cod3vstudio.bidreminder.util;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by Administrator on 10/12/2016.
 */
public class AppFirebaseInstanceIDService extends FirebaseInstanceIdService {

    private static final String TAG = "FIREBASE";

    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Refreshed token: " + refreshedToken);
    }

    private void sendRegistrationToServer(String token) {
    }
}
