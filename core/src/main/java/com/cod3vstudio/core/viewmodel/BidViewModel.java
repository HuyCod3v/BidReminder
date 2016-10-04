package com.cod3vstudio.core.viewmodel;

import android.databinding.Bindable;

import com.cod3vstudio.core.BR;
import com.cod3vstudio.core.model.entities.Product;
import com.cod3vstudio.core.model.services.clouds.ServiceComponent;
import com.cod3vstudio.core.model.services.storages.ModelComponent;
import com.cod3vstudio.core.view.INavigator;

import org.greenrobot.eventbus.Subscribe;

/**
 * Created by Administrator on 8/1/2016.
 */
public class BidViewModel extends BaseViewModel {

    //region Properties

    private ServiceComponent mServiceComponent;

    private ModelComponent mModelComponent;

    private Product mProduct;

    private String mBidPrice;

    //endregion

    //region Constructors

    public BidViewModel(INavigator navigator, ModelComponent modelComponent, ServiceComponent serviceComponent) {
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
    public String getBidPrice() {
        return mBidPrice;
    }


    public void setBidPrice(String bidPrice) {
        mBidPrice = bidPrice;
        notifyPropertyChanged(BR.bidPrice);
    }

    //endregion

    //region Lifecycle

    @Override
    public void onCreate() {
        super.onCreate();
        register();
        mBidPrice = "";
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
        mBidPrice = null;
    }

    //endregion

    //region Subscribe method

    @Subscribe(sticky = true)
    public void event(Product product) {
        setProduct(product);
    }

    //endregion



}
