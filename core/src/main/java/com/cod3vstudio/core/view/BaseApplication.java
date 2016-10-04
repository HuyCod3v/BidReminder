package com.cod3vstudio.core.view;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import com.cod3vstudio.core.model.entities.User;

/**
 * Created by Administrator on 7/25/2016.
 */

public class BaseApplication extends Application implements  Application.ActivityLifecycleCallbacks {

    //region Properties

    private Activity mCurrentActivity;

    private User mSignedInUser;

    private static String sLocale = "vi";

    //endregion

    //region Setters and Getters



    public static String getLocale() {
        return sLocale;
    }

    public boolean isSignedUserAvailable() {
        return mSignedInUser != null;
    }

    public User getSignedInUser() {
        return mSignedInUser;
    }

    public void setSignedInUser(User signedInUser) {
        mSignedInUser = signedInUser;
    }

    public Activity getCurrentActivity() {
        return mCurrentActivity;
    }

    public boolean isCurrentActivityAvailable() {
        return mCurrentActivity != null;
    }

    //endregion

    //region Application Lifecycle

    @Override
    public void onCreate() {
        super.onCreate();
        registerActivityLifecycleCallbacks(this);
    }

    //endregion

    //region ActivityLifecycleCallback implements

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        if (mCurrentActivity != activity) {
            mCurrentActivity = activity;
        }
    }

    @Override
    public void onActivityStarted(Activity activity) {
        if (mCurrentActivity != activity) {
            mCurrentActivity = activity;
        }
    }

    @Override
    public void onActivityResumed(Activity activity) {

    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {

    }

    //endregion

}
