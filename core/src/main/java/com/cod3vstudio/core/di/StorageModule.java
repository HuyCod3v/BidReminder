package com.cod3vstudio.core.di;


import com.cod3vstudio.core.model.services.storages.ISavedModel;
import com.cod3vstudio.core.model.services.storages.IUserModel;
import com.cod3vstudio.core.model.services.storages.ModelComponent;
import com.cod3vstudio.core.model.services.storages.SavedModel;
import com.cod3vstudio.core.model.services.storages.UserModel;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 7/26/2016.
 */

@Module
public class StorageModule {

    //region Provide methods

    @Provides
    @Singleton
    public IUserModel providesUserModel() {
        return new UserModel();
    }

    @Provides
    @Singleton
    public ISavedModel providesSavedModel() {
        return new SavedModel();
    }


    @Provides
    @Singleton
    public ModelComponent providesModelComponent(IUserModel userModel, ISavedModel savedModel) {
        return new ModelComponent(userModel, savedModel);
    }

    //endregion

}
