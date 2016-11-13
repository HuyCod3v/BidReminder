package com.cod3vstudio.core.viewmodel;

import android.databinding.Bindable;
import android.text.TextUtils;
import android.util.Log;

import com.cod3vstudio.core.BR;
import com.cod3vstudio.core.model.entities.User;
import com.cod3vstudio.core.model.responses.APIResponse;
import com.cod3vstudio.core.model.services.clouds.ServiceComponent;
import com.cod3vstudio.core.model.services.storages.ModelComponent;
import com.cod3vstudio.core.view.INavigator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 8/1/2016.
 */

public class EditPasswordViewModel extends BaseViewModel {

    //region Properties

    private ServiceComponent mServiceComponent;

    private ModelComponent mModelComponent;

    private String mNewPassword;

    private String mRetypePassword;

    private static  final String TAG = "EditPassword";

    //endregion

    //region Constructors

    public String getRetypePassword() {
        return mRetypePassword;
    }

    public void setRetypePassword(String retypePassword) {
        mRetypePassword = retypePassword;
    }

    public String getNewPassword() {
        return mNewPassword;
    }

    public void setNewPassword(String newPassword) {
        mNewPassword = newPassword;
    }

    public EditPasswordViewModel(INavigator navigator, ModelComponent modelComponent, ServiceComponent serviceComponent) {
        super(navigator);
        mModelComponent = modelComponent;
        mServiceComponent = serviceComponent;
    }

    //endregion

    //region Private methods

    private boolean validateForm(){
        if(mNewPassword.trim().equals("") || mRetypePassword.trim().equals("")){
            showMessage("Vui lòng nhập đầy đủ thông tin");
            return false;
        }else if(mNewPassword.trim().length()<6 || mRetypePassword.trim().length()<6){
            showMessage("Mật khẩu phải từ 6 đến 50");
            return false;
        }else if(!mNewPassword.trim().equals(mRetypePassword.trim())){
            showMessage("Mật khẩu không trùng khớp nhau");
            return false;
        }
        return true;
    }

    //endregion

    //region Public methods

    public void changePassword(){
        if(validateForm()){
            mServiceComponent.getUserService().updatePassword(getNavigator().getApplication().getSignedInUser().getId(),mNewPassword).enqueue(new Callback<APIResponse<User>>() {
                @Override
                public void onResponse(Call<APIResponse<User>> call, Response<APIResponse<User>> response) {
                    if(response.body().isSuccess()){
                        Log.d(TAG,"Change password successfully !");
                        showMessage("Bạn đã đổi thành công mật khẩu");
                        getCurrentActivity().finish();
                    }else{
                        Log.d(TAG,"Change password failed");
                    }
                }

                @Override
                public void onFailure(Call<APIResponse<User>> call, Throwable t) {
                    Log.d(TAG,"Change password failed");
                }
            });
        }
    }

    //endregion

    //region Lifecycle

    @Override
    public void onCreate() {
        super.onCreate();
        mNewPassword="";
        mRetypePassword="";
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

}
