package com.cod3vstudio.core.view;

import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;

import com.cod3vstudio.core.viewmodel.BaseViewModel;

import javax.inject.Inject;

/**
 * Created by quanghuymr403 on 27/09/2016.
 */

public class BaseDialog<B extends ViewDataBinding, V extends BaseViewModel> extends DialogFragment {

    //region Properties

    protected B mViewDataBinding;

    @Inject
    protected V mViewModel;

    //endregion


    //region Lifecycle


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (mViewModel != null) {
            mViewModel.onCreate();
        }
    }

    @Override
    public void onStop() {
        super.onStop();

        if (mViewModel != null) {
            mViewModel.onStop();
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        if (mViewModel != null) {
            mViewModel.onStart();
        }
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);

        if (mViewModel != null) {
            mViewModel.onDestroy();
        }
    }

    //endregion

    //region Protected Methods

    protected void setBindingContentView(int layoutResId, int variableId) {
        mViewDataBinding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), layoutResId, null, false);
        mViewDataBinding.setVariable(variableId, mViewModel);
    }

    //endregion
}
