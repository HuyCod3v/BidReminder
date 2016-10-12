package com.cod3vstudio.core.viewmodel;

import android.databinding.Bindable;
import android.util.Patterns;

import com.cod3vstudio.core.BR;
import com.cod3vstudio.core.R;
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

public class EditProfileViewModel extends BaseViewModel {

    //region Properties

    private ServiceComponent mServiceComponent;

    private String mName;
    private String mEmail;
    private String mPhone;
    private String mAddress;
    private String mNation;

    private ModelComponent mModelComponent;

    //endregion

    //region Constructors

    public EditProfileViewModel(INavigator navigator, ModelComponent modelComponent, ServiceComponent serviceComponent) {
        super(navigator);
        mModelComponent = modelComponent;
        mServiceComponent = serviceComponent;
    }

    //endregion

    //region Getters and Setters

    @Bindable
    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
        notifyPropertyChanged(BR.name);
    }

    @Bindable
    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
        notifyPropertyChanged(BR.email);
    }

    @Bindable
    public String getPhone() {
        return mPhone;
    }

    public void setPhone(String phone) {
        mPhone = phone;
        notifyPropertyChanged(BR.phone);
    }

    @Bindable
    public String getAddress() {
        return mAddress;
    }

    public void setAddress(String address) {
        mAddress = address;
        notifyPropertyChanged(BR.address);
    }

    @Bindable
    public String getNation() {
        return mNation;
    }

    public void setNation(String nation) {
        mNation = nation;
        notifyPropertyChanged(BR.nation);
    }


    //endregion

    //region Lifecycle

    @Override
    public void onCreate() {
        super.onCreate();
        User user = getNavigator().getApplication().getSignedInUser();
        setName(user.getName());
        setAddress(user.getAddress());
        setEmail(user.getEmail());
        setPhone(user.getPhone());
        setNation(user.getNation());
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
        mName = null;
        mAddress = null;
        mEmail = null;
        mPhone = null;
        mNation = null;
    }

    //endregion

    //region Public methods

    public void updateProfileCommand() {
        if (validateInput()) {
            User user = getNavigator().getApplication().getSignedInUser();
            getNavigator().showBusyIndicator(getCurrentActivity().getString(R.string.loading));
            mServiceComponent.getUserService().updateProfile(user.getId(), mEmail, mName, mPhone, mAddress, mNation)
                            .enqueue(new Callback<APIResponse<User>>() {
                                @Override
                                public void onResponse(Call<APIResponse<User>> call, Response<APIResponse<User>> response) {
                                    if (response.isSuccessful() && response.body() != null && response.body().isSuccess()) {
                                        getNavigator().goBack();
                                        getNavigator().getApplication().setSignedInUser(response.body().getData());
                                        showMessage("Cập nhập thông tin cá nhân thành công");
                                    } else {
                                        showMessage(getCurrentActivity().getString(R.string.error_occurred));
                                    }
                                    getNavigator().hideBusyIndicator();
                                }

                                @Override
                                public void onFailure(Call<APIResponse<User>> call, Throwable t) {
                                    showMessage(getCurrentActivity().getString(R.string.error_occurred));
                                    getNavigator().hideBusyIndicator();
                                }
                            });
        }
    }

    //endregion

    //region Private methods

    private boolean validateInput() {
        if (!Patterns.EMAIL_ADDRESS.matcher(mEmail).matches()) {
            showMessage("Email không hợp lệ");
            return false;
        }

        if (mName.trim().length() < 6 || mName.trim().length() > 50) {
            showMessage("Tên hiển thị phải từ 6 đến 50 ký tự");
            return false;
        }

        if (mPhone.trim().length() < 8 || mName.trim().length() > 20) {
            showMessage("Tên hiển thị phải từ 8 đến 20 ký tự");
            return false;
        }

        if (mAddress.equals("")) {
            showMessage("Địa chỉ liên hệ không được để trống");
            return false;
        }

        return true;
    }

    //endregion
}
