package com.cod3vstudio.core.model.services.storages;

/**
 * Created by quanghuymr403 on 24/09/2016.
 */
public class ModelComponent {
    private final IUserModel mUserModel;

    private final ISavedModel mSavedModel;

    private final IChangeModel mChangeModel;

    public ModelComponent(IUserModel userModel, ISavedModel savedModel, IChangeModel changeModel) {
        mUserModel = userModel;
        mChangeModel = changeModel;
        mSavedModel = savedModel;
    }

    public IUserModel getUserModel() {
        return mUserModel;
    }

    public ISavedModel getSavedModel() {
        return mSavedModel;
    }

    public IChangeModel getChangeModel() {
        return mChangeModel;
    }
}
