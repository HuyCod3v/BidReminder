package com.cod3vstudio.core.viewmodel;

import android.databinding.Bindable;

import com.cod3vstudio.core.BR;
import com.cod3vstudio.core.model.entities.Bidding;
import com.cod3vstudio.core.model.entities.Product;
import com.cod3vstudio.core.model.responses.APIResponse;
import com.cod3vstudio.core.model.services.clouds.ServiceComponent;
import com.cod3vstudio.core.model.services.storages.ModelComponent;
import com.cod3vstudio.core.view.INavigator;

import org.greenrobot.eventbus.Subscribe;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

    @Override
    public INavigator getNavigator() {
        return super.getNavigator();
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

    //region Public methods

    public void bid(boolean isBuyAutomatically) {
        int isBuyAutomaticallyInt = isBuyAutomatically ? 1 : 0;
        Bidding bidding = initiateBidding(isBuyAutomatically);
        getNavigator().showBusyIndicator("Đang tải");
        mServiceComponent.getBiddingService().add(bidding.getBidPrice()
                , bidding.getLastPrice()
                , isBuyAutomaticallyInt
                , bidding.getUserId()
                , mProduct.getItemId()
                , mProduct.getRepositoryId()
                , mProduct.getName()
                , mProduct.getImage()
                , mProduct.getCurrencyUnit()
                , "Des")
                .enqueue(new Callback<APIResponse<Bidding>>() {
            @Override
            public void onResponse(Call<APIResponse<Bidding>> call, Response<APIResponse<Bidding>> response) {
                if (response.isSuccessful() && response.body() != null && response.body().isSuccess()) {
                    showMessage("Success");
                } else {
                    showMessage("Fail 1");
                }
                getNavigator().hideBusyIndicator();
            }

            @Override
            public void onFailure(Call<APIResponse<Bidding>> call, Throwable t) {
                showMessage("Fail2");
                getNavigator().hideBusyIndicator();
            }
        });
    }

    public boolean isSignedUserAvailable() {
        return getNavigator().getApplication().isSignedUserAvailable();
    }

    //endregion

    //region Private methods

    private Bidding initiateBidding(boolean isBuyAutomatically) {

        Bidding bidding = new Bidding();
        bidding.setProductId(mProduct.getId());
        bidding.setBidPrice(Double.valueOf(mBidPrice));
        bidding.setLastPrice(mProduct.getPrice());
        bidding.setRepositoryId(mProduct.getRepositoryId());
        bidding.setUserId(getNavigator().getApplication().getSignedInUser().getId());
        bidding.setIsBuyAutomatically(isBuyAutomatically);
        return  bidding;

    }

    //endregion

}
