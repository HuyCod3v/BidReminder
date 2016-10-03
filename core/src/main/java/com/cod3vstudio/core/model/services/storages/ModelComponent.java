package com.cod3vstudio.core.model.services.storages;

/**
 * Created by quanghuymr403 on 24/09/2016.
 */
public class ModelComponent {
    private final IUserModel mUserModel;

    public ModelComponent(IUserModel userModel) {
        mUserModel = userModel;
    }

    public IUserModel getUserModel() {
        return mUserModel;
    }
}
