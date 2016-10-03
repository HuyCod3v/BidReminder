package com.cod3vstudio.core.view;

import android.content.res.Configuration;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;


import com.cod3vstudio.core.viewmodel.BaseViewModel;

import java.util.Locale;

import javax.inject.Inject;

/**
 * Created by Administrator on 7/25/2016.
 */
public abstract class BaseActivity<B extends ViewDataBinding, V extends BaseViewModel> extends AppCompatActivity {

    //region Properties

    private Toolbar mToolbar;

    protected B mViewDataBinding;

    @Inject
    protected V mViewModel;

    //endregion

    //region Lifecycle

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLanguage(BaseApplication.getLocale());

        if (mViewModel != null) {
            mViewModel.onCreate();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mViewModel != null) {
            mViewModel.onStart();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();

        if (mViewModel != null) {
            mViewModel.onStop();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (mViewModel != null) {
            mViewModel.onDestroy();
        }
    }

    //endregion

    //region Protected Methods

    protected void setBindingContentView(int layoutResId, int variableId) {
        mViewDataBinding = DataBindingUtil.setContentView(this, layoutResId);
        mViewDataBinding.setVariable(variableId, mViewModel);
    }

    protected void setToolbar(int resId) {
        mToolbar = (Toolbar) findViewById(resId);
        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
        }
    }

    protected final void register() {
        org.greenrobot.eventbus.EventBus eventBus = org.greenrobot.eventbus.EventBus.getDefault();
        if (!eventBus.isRegistered(this)) {
            eventBus.register(this);
        }
    }

    protected final void unregister() {
        org.greenrobot.eventbus.EventBus eventBus = org.greenrobot.eventbus.EventBus.getDefault();
        if (eventBus.isRegistered(this)) {
            eventBus.unregister(this);
        }

    }

    protected final void setLanguage(String language) {
        Locale locale = new Locale(language);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config,
                getBaseContext().getResources().getDisplayMetrics());
    }

    //endregion

    //region Public Methods

    //endregion

}
