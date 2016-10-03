package com.cod3vstudio.core.viewmodel;

import android.app.Activity;
import android.databinding.BaseObservable;
import android.widget.Toast;

import com.cod3vstudio.core.view.INavigator;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by Administrator on 7/25/2016.
 */

public abstract class BaseViewModel extends BaseObservable implements IViewModelLifecycle {

    //region Properties

    private INavigator mNavigator;

    //endregion

    //region Setters and Getters

    protected Activity getCurrentActivity() {
        return getNavigator().getApplication().getCurrentActivity();
    }

    protected INavigator getNavigator() {
        return mNavigator;
    }

    //endregion

    //region Constructors;

    protected BaseViewModel(INavigator navigator) {
        mNavigator = navigator;
    }

    protected BaseViewModel() {
        super();
    }

    //endregion

    //region IViewModelLifecycle implements

    @Override
    public void onCreate() {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroy() {

    }

    //endregion

    //region Protected methods

    protected static final void post(Object event) {
        EventBus.getDefault().post(event);
    }

    protected  static final void postSticky(Object event) {
        EventBus.getDefault().postSticky(event);
    }

    protected final void register() {
        EventBus eventBus = EventBus.getDefault();
        if (!eventBus.isRegistered(this)) {
            eventBus.register(this);
        }
    }

    protected final void unregister() {
        EventBus eventBus = EventBus.getDefault();
        if (eventBus.isRegistered(this)) {
            eventBus.unregister(this);
        }

    }

    protected final void showMessage(String message) {
        Toast.makeText(getNavigator().getApplication().getCurrentActivity(), message, Toast.LENGTH_LONG).show();
    }

    //endregion

}
