package com.cod3vstudio.core.viewmodel;

import android.content.Intent;

import com.cod3vstudio.core.model.services.clouds.ServiceComponent;
import com.cod3vstudio.core.model.services.storages.ModelComponent;
import com.cod3vstudio.core.util.Constants;
import com.cod3vstudio.core.view.INavigator;

/**
 * Created by Administrator on 8/1/2016.
 */
public class ProfileViewModel extends BaseViewModel {

    //region Properties

    private ServiceComponent mServiceComponent;

    private ModelComponent mModelComponent;

    //endregion

    //region Constructors

    public ProfileViewModel(INavigator navigator, ModelComponent modelComponent, ServiceComponent serviceComponent) {
        super(navigator);
        mModelComponent = modelComponent;
        mServiceComponent = serviceComponent;
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

    //region Puclic methods

    public void pickImageCommand() {
        Intent getIntent = new Intent(Intent.ACTION_GET_CONTENT);
        getIntent.setType("image/*");

        Intent pickIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        pickIntent.setType("image/*");

        Intent chooserIntent = Intent.createChooser(getIntent, "Select image");
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[] {pickIntent});

        getCurrentActivity().startActivityForResult(chooserIntent, Constants.PICK_IMAGE_COMMAND);
    }

    public void showEditProfileCommand() {
        getNavigator().navigateTo(Constants.EDIT_PROFILE_PAGE);
    }

    public void showEditPasswordCommand() {
        getNavigator().navigateTo(Constants.EDIT_PASSWORD_PAGE);
    }

    //endregion

}
