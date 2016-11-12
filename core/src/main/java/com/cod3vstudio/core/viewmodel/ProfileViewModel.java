package com.cod3vstudio.core.viewmodel;

import android.content.Intent;
import android.databinding.Bindable;
import android.net.Uri;
import android.os.Build;
import android.util.Log;

import com.cod3vstudio.core.BR;
import com.cod3vstudio.core.R;
import com.cod3vstudio.core.model.entities.User;
import com.cod3vstudio.core.model.responses.APIResponse;
import com.cod3vstudio.core.model.services.clouds.ServiceComponent;
import com.cod3vstudio.core.model.services.storages.ModelComponent;
import com.cod3vstudio.core.util.Constants;
import com.cod3vstudio.core.view.INavigator;
import com.google.common.net.MediaType;

import org.apache.commons.io.FileUtils;

import java.io.File;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 8/1/2016.
 */
public class ProfileViewModel extends BaseViewModel {

    //region Properties

    private ServiceComponent mServiceComponent;

    private User user;

    private ModelComponent mModelComponent;

    //endregion

    //region Constructors

    public ProfileViewModel(INavigator navigator, ModelComponent modelComponent, ServiceComponent serviceComponent) {
        super(navigator);
        mModelComponent = modelComponent;
        mServiceComponent = serviceComponent;
    }

    //endregion

    //region Getters and Setters

    @Bindable
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
        notifyPropertyChanged(BR.user);
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
        setUser(getNavigator().getApplication().getSignedInUser());
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

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1) {
            String[] permissions = {"android.permission.READ_EXTERNAL_STORAGE"};

            int permsRequestCode = 200;

            getCurrentActivity().requestPermissions(permissions, permsRequestCode);
            return;
        }

        pickImage();

    }

    public void pickImage() {
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

    public void uploadAvatar(String filePath) {

        File file = new File(filePath);

        RequestBody requestFile =
                RequestBody.create(okhttp3.MediaType.parse("multipart/form-data"), file);

        MultipartBody.Part body =
                MultipartBody.Part.createFormData("picture", file.getName(), requestFile);

        Call<APIResponse<Boolean>> call = mServiceComponent.getUserService().uploadAvatar(body);
        call.enqueue(new Callback<APIResponse<Boolean>>() {
            @Override
            public void onResponse(Call<APIResponse<Boolean>> call,
                                   Response<APIResponse<Boolean>> response) {
                Log.v("Upload", "success");
            }

            @Override
            public void onFailure(Call<APIResponse<Boolean>> call, Throwable t) {
                Log.e("Upload error:", t.getMessage());
            }
        });
    }

    //endregion

}
