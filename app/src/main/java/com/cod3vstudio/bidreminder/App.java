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
import com.cod3vstudio.core.di.AppModule;
import com.cod3vstudio.core.di.CloudModule;
import com.cod3vstudio.core.di.ViewModelModule;
import com.cod3vstudio.core.util.Constants;
import com.cod3vstudio.core.view.BaseApplication;
import com.cod3vstudio.core.view.INavigator;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by Administrator on 7/25/2016.
 */

public class App extends BaseApplication {

    //region Properties

    private static AppComponent sAppComponent;

    //endregion

    //region Getter and Setter
    
    public synchronized static AppComponent sharedComponent() {
        return sAppComponent;
    }

    //endregion

    //region Lifecycle

    @Override
    public void onCreate() {
        super.onCreate();

        AppModule appModule = new AppModule(this);
        INavigator navigator = appModule.getNavigator();
        navigator.configure(Constants.MAIN_PAGE, MainActivity.class);
        navigator.configure(Constants.SIGN_UP_PAGE, SignUpActivity.class);
        navigator.configure(Constants.SIGN_IN_PAGE, SignInActivity.class);
        navigator.configure(Constants.PRODUCT_PAGE, ProductActivity.class);
        navigator.configure(Constants.PROFILE_PAGE, ProfileActivity.class);
        navigator.configure(Constants.EDIT_PROFILE_PAGE, EditProfileActivity.class);
        navigator.configure(Constants.EDIT_PASSWORD_PAGE, EditPasswordActivity.class);
        navigator.configure(Constants.SETTINGS_PAGE, SettingsActivity.class);
        navigator.configure(Constants.FILTER_PAGE, FilterActivity.class);

        sAppComponent = DaggerAppComponent.builder()
                .appModule(appModule)
                .cloudModule(new CloudModule())
                .viewModelModule(new ViewModelModule())
                .build();

        Realm.init(getApplicationContext());
        RealmConfiguration realmConfig = new RealmConfiguration.Builder().deleteRealmIfMigrationNeeded().build();
        Realm.setDefaultConfiguration(realmConfig);

    }

    //endregion



}


