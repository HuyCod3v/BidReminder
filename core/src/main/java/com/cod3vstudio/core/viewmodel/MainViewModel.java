package com.cod3vstudio.core.viewmodel;

import com.cod3vstudio.core.view.INavigator;

/**
 * Created by Administrator on 7/26/2016.
 */

public class MainViewModel extends BaseViewModel {

    //region Properties

    //endregion

    //region Constructors

    public MainViewModel(INavigator navigator) {
        super(navigator);
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

    //endregion
}
