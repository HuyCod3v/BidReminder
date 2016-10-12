package com.cod3vstudio.core.viewmodel;

import android.databinding.Bindable;

import com.cod3vstudio.core.BR;
import com.cod3vstudio.core.model.entities.Product;
import com.cod3vstudio.core.model.entities.Saved;
import com.cod3vstudio.core.model.services.clouds.ServiceComponent;
import com.cod3vstudio.core.model.services.storages.ModelComponent;
import com.cod3vstudio.core.util.Constants;
import com.cod3vstudio.core.view.INavigator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by quanghuymr403 on 22/09/2016.
 */
public class SavedViewModel extends BaseViewModel {

    //region Properties

    private List<Saved> mSaveds;

    private ModelComponent mModelComponent;

    private ServiceComponent mServiceComponent;

    //endregion

    //region Constructors

    public SavedViewModel(INavigator navigator, ModelComponent modelComponent, ServiceComponent serviceComponent) {
        super(navigator);

        mModelComponent = modelComponent;

        mServiceComponent = serviceComponent;
    }

    //endregion

    //region Getters and Setters

    @Bindable
    public List<Saved> getSaveds() {
        return mSaveds;
    }


    public void setSaveds(List<Saved> mSaveds) {
        this.mSaveds = mSaveds;

        notifyPropertyChanged(BR.saveds);
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

        loadSaveds();
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

    //region Public methods

    public void loadSaveds() {
        List<Saved> saveds = mModelComponent.getSavedModel().findAll();

        setSaveds(saveds);
    }

    public void showProductDetailsCommand(Saved saved) {
        getNavigator().navigateTo(Constants.PRODUCT_PAGE);
        postSticky(saved);
    }

    //endregion

}
