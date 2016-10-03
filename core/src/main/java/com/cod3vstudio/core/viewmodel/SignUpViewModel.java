package com.cod3vstudio.core.viewmodel;

import android.util.Patterns;

import com.cod3vstudio.core.model.entities.User;
import com.cod3vstudio.core.view.INavigator;

/**
 * Created by Administrator on 8/3/2016.
 */
public class SignUpViewModel extends BaseViewModel {

    //region Properties

    private String mEmail;

    private String mPassword;

    private String mRetypePassword;

    private String mName;

    //endregion

    //region Getter and Setter

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        mPassword = password;
    }

    public String getRetypePassword() {
        return mRetypePassword;
    }

    public void setRetypePassword(String retypePassword) {
        mRetypePassword = retypePassword;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    //endregion

    //region Constructors

    public SignUpViewModel(INavigator navigator) {
        super(navigator);
    }

    //endregion

    //region Lifecycle

    @Override
    public void onCreate() {
        super.onCreate();

        mEmail = "";
        mPassword = "";
        mRetypePassword = "";
        mName = "";
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

        mEmail = null;
        mPassword = null;
        mRetypePassword = null;
        mName = null;
    }

    //endregion

    //region Public Methods

    public boolean validateInput() {

        if (!Patterns.EMAIL_ADDRESS.matcher(mEmail).matches()) {
            showMessage("Email không hợp lệ");
            return false;
        }

        if (mPassword.trim().length() < 6 || mPassword.trim().length() > 50) {
            showMessage("Mật khẩu phải từ 6 đến 50 ký tự");
            return false;
        }

        if (!mPassword.trim().equals(mRetypePassword.trim())) {
            showMessage("Mật khẩu và mật khẩu nhập lại không trùng");
            return false;
        }

        if (mName.trim().length() < 6 || mName.trim().length() > 50) {
            showMessage("Tên hiển thị phải từ 6 đến 50 ký tự");
            return false;
        }

        return true;
    }

    public void signUpCommand() {
        if (validateInput()) {
            User user = new User();
            user.setEmail(mEmail);
            user.setPassword(mPassword);
            user.setName(mName);
        }
    }

    public void showSignInCommand() {
        getNavigator().goBack();
    }

    //endregion
}
