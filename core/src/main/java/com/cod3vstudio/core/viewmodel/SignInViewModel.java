package com.cod3vstudio.core.viewmodel;

import android.util.Patterns;

import com.cod3vstudio.core.model.entities.User;
import com.cod3vstudio.core.util.Constants;
import com.cod3vstudio.core.view.INavigator;
/**
 * Created by Administrator on 8/1/2016.
 */
public class SignInViewModel extends BaseViewModel {

    //region Properties

    private String mPassword;

    private String mEmail;

    //endregion

    //region Getter and Setter

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        mPassword = password;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    //endregion

    //region Constructors

    public SignInViewModel(INavigator navigator) {
        super(navigator);
    }

    //endregion

    //region Lifecycle

    @Override
    public void onCreate() {
        super.onCreate();

        mEmail = "";
        mPassword = "";
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
    }

    //endregion

    //region Private Methods

    private boolean validateInput() {
        if (!Patterns.EMAIL_ADDRESS.matcher(mEmail).matches()) {
            showMessage("Email không hợp lệ");
            return false;
        }

        if (mPassword.trim().length() < 6 || mPassword.length() > 50) {
            showMessage("Mật khẩu phải từ 6 tới 50 ký tự");
            return false;
        }

        return true;
    }

    //endregion

    //region Public Methods

    public void showRegister() {
        getNavigator().navigateTo(Constants.SIGN_UP_PAGE);
    }

    public void signInCommand() {
        if (validateInput()) {
            User user = new User();
            user.setEmail(mEmail);
            user.setPassword(mPassword);
        }
    }

    //endregion

}
