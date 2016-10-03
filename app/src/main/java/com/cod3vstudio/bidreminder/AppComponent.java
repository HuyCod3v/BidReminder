package com.cod3vstudio.bidreminder;

import com.cod3vstudio.bidreminder.activities.EditPasswordActivity;
import com.cod3vstudio.bidreminder.activities.EditProfileActivity;
import com.cod3vstudio.bidreminder.activities.FilterActivity;
import com.cod3vstudio.bidreminder.activities.MainActivity;
import com.cod3vstudio.bidreminder.activities.ProductActivity;
import com.cod3vstudio.bidreminder.activities.ProfileActivity;
import com.cod3vstudio.bidreminder.activities.SettingsActivity;
import com.cod3vstudio.bidreminder.activities.SignInActivity;
import com.cod3vstudio.bidreminder.activities.SignUpActivity;
import com.cod3vstudio.bidreminder.fragments.BiddingFragment;
import com.cod3vstudio.bidreminder.fragments.CartFragment;
import com.cod3vstudio.bidreminder.fragments.CategoryFragment;
import com.cod3vstudio.bidreminder.fragments.HomeFragment;
import com.cod3vstudio.bidreminder.fragments.SavedFragment;
import com.cod3vstudio.core.di.ViewModelModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Administrator on 7/30/2016.
 */

@Singleton
@Component(modules = {ViewModelModule.class })
public interface AppComponent {

    //region Activities

    void inject(MainActivity mainActivity);

    void inject(EditProfileActivity editProfileActivity);

    void inject(SignInActivity signInActivity);

    void inject(SignUpActivity signUpActivity);

    void inject(ProductActivity productActivity);

    void inject(ProfileActivity profileActivity);

    void inject(EditPasswordActivity editPasswordActivity);

    void inject(SettingsActivity settingsActivity);

    void inject(FilterActivity filterActivity);

    //endregion

    //region Fragments

    void inject(CategoryFragment categoryFragment);

    void inject(SavedFragment savedFragment);

    void inject(BiddingFragment biddingFragment);

    void inject(CartFragment cartFragment);

    void inject(HomeFragment homeFragment);

    //endregion

}
