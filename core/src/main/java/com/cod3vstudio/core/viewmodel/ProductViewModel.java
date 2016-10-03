package com.cod3vstudio.core.viewmodel;

import android.databinding.Bindable;

import com.cod3vstudio.core.BR;
import com.cod3vstudio.core.model.entities.Change;
import com.cod3vstudio.core.model.entities.Product;
import com.cod3vstudio.core.model.services.clouds.ServiceComponent;
import com.cod3vstudio.core.model.services.storages.ModelComponent;
import com.cod3vstudio.core.view.INavigator;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 7/29/2016.
 */

public class ProductViewModel extends BaseViewModel {

    //region Properties

    private Product mProduct;

    private ModelComponent mModelComponent;

    private ServiceComponent mServiceComponent;

    private List<Change> mChanges;

    //endregion

    //region Constructors

    public ProductViewModel(INavigator navigator, ModelComponent modelComponent, ServiceComponent serviceComponent) {
        super(navigator);

        mModelComponent = modelComponent;

        mServiceComponent = serviceComponent;
    }

    //endregion

    //region Getters and Setters

    @Bindable
    public Product getProduct() {
        return mProduct;
    }

    public void setProduct(Product product) {
        mProduct = product;

        notifyPropertyChanged(BR.product);
    }

    @Bindable
    public List<Change> getChanges() {
        return mChanges;
    }

    public void setChanges(List<Change> changes) {
        mChanges = changes;

        notifyPropertyChanged(BR.changes);
    }

    //endregion

    //region Lifecycle

    @Override
    public void onCreate() {
        super.onCreate();
        register();
        float[] prices = {120000, 130000, 170000, 140000, 100000, 70000};
        List<Change> changes = new ArrayList<>();
        for (int i = 0; i < prices.length; i++) {
            Change change = new Change();
            change.setPrice(prices[i]);
            change.setId(i);
            change.setChangedAt(new Date());
            changes.add(change);
        }

        setChanges(changes);
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
        unregister();

        mProduct = null;
    }

    //endregion

    //region Public methods

    //endregion

    //region Subscribe methods

    @Subscribe(sticky = true)
    public void event(Product product) {
        setProduct(product);
        getCurrentActivity().setTitle(mProduct.getName());
    }

    //endregion
}
