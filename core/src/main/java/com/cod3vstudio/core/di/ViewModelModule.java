package com.cod3vstudio.core.di;


import com.cod3vstudio.core.model.services.clouds.ServiceComponent;
import com.cod3vstudio.core.model.services.storages.ModelComponent;
import com.cod3vstudio.core.view.INavigator;
import com.cod3vstudio.core.viewmodel.BidViewModel;
import com.cod3vstudio.core.viewmodel.BiddingViewModel;
import com.cod3vstudio.core.viewmodel.CartViewModel;
import com.cod3vstudio.core.viewmodel.CategoryViewModel;
import com.cod3vstudio.core.viewmodel.EditPasswordViewModel;
import com.cod3vstudio.core.viewmodel.EditProfileViewModel;
import com.cod3vstudio.core.viewmodel.FilterViewModel;
import com.cod3vstudio.core.viewmodel.HomeViewModel;
import com.cod3vstudio.core.viewmodel.MainViewModel;
import com.cod3vstudio.core.viewmodel.ProductViewModel;
import com.cod3vstudio.core.viewmodel.ProfileViewModel;
import com.cod3vstudio.core.viewmodel.SavedViewModel;
import com.cod3vstudio.core.viewmodel.SettingsViewModel;
import com.cod3vstudio.core.viewmodel.SignInViewModel;
import com.cod3vstudio.core.viewmodel.SignUpViewModel;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 7/30/2016.
 */

@Module(includes = { CloudModule.class, StorageModule.class })
public class ViewModelModule {

    //region Provide methods

    @Provides
    @Singleton
    MainViewModel providesMainViewModel(INavigator navigator, ModelComponent modelComponent, ServiceComponent serviceComponent) {
        return new MainViewModel(navigator, modelComponent, serviceComponent);
    }

    @Provides
    @Singleton
    CategoryViewModel providesCategoryViewModel(INavigator navigator) {
        return new CategoryViewModel(navigator);
    }

    @Provides
    @Singleton
    SavedViewModel providesSavedViewModel(INavigator navigator, ModelComponent modelComponent, ServiceComponent serviceComponent) {
        return new SavedViewModel(navigator, modelComponent, serviceComponent);
    }

    @Provides
    @Singleton
    BiddingViewModel providesBiddingViewModel(INavigator navigator, ModelComponent modelComponent, ServiceComponent serviceComponent) {
        return new BiddingViewModel(navigator, modelComponent, serviceComponent);
    }

    @Provides
    @Singleton
    CartViewModel providesCartViewModel(INavigator navigator, ModelComponent modelComponent, ServiceComponent serviceComponent) {
        return new CartViewModel(navigator, modelComponent, serviceComponent);
    }

    @Provides
    @Singleton
    HomeViewModel providesHomeViewModel(INavigator navigator, ModelComponent modelComponent, ServiceComponent serviceComponent) {
        return new HomeViewModel(navigator, modelComponent, serviceComponent);
    }

    @Provides
    @Singleton
    SignInViewModel providesSignInViewModel(INavigator navigator, ModelComponent modelComponent, ServiceComponent serviceComponent) {
        return new SignInViewModel(navigator, modelComponent, serviceComponent);
    }

    @Provides
    @Singleton
    SignUpViewModel providesSignUpViewModel(INavigator navigator, ModelComponent modelComponent, ServiceComponent serviceComponent) {
        return new SignUpViewModel(navigator, modelComponent, serviceComponent);

    }

    @Provides
    @Singleton
    ProductViewModel providesProductViewModel(INavigator navigator, ModelComponent modelComponent, ServiceComponent serviceComponent) {
        return new ProductViewModel(navigator, modelComponent, serviceComponent);
    }

    @Provides
    @Singleton
    ProfileViewModel providesProfileViewModel(INavigator navigator, ModelComponent modelComponent, ServiceComponent serviceComponent) {
        return new ProfileViewModel(navigator, modelComponent, serviceComponent);
    }

    @Provides
    @Singleton
    SettingsViewModel providesSettingsViewModel(INavigator navigator, ModelComponent modelComponent, ServiceComponent serviceComponent) {
        return new SettingsViewModel(navigator, modelComponent, serviceComponent);
    }

    @Provides
    @Singleton
    EditProfileViewModel providesEditProfileViewModel(INavigator navigator, ModelComponent modelComponent, ServiceComponent serviceComponent) {
        return new EditProfileViewModel(navigator, modelComponent, serviceComponent);
    }

    @Provides
    @Singleton
    EditPasswordViewModel providesEditPasswordViewModel(INavigator navigator, ModelComponent modelComponent, ServiceComponent serviceComponent) {
        return new EditPasswordViewModel(navigator, modelComponent, serviceComponent);
    }

    @Provides
    @Singleton
    FilterViewModel providesFilterViewModel(INavigator navigator, ModelComponent modelComponent, ServiceComponent serviceComponent) {
        return new FilterViewModel(navigator, modelComponent, serviceComponent);
    }

    @Provides
    @Singleton
    BidViewModel providesBidViewModel(INavigator navigator, ModelComponent modelComponent, ServiceComponent serviceComponent) {
        return new BidViewModel(navigator, modelComponent, serviceComponent);
    }

    //endregion

}
