package com.cod3vstudio.core.viewmodel;

import android.databinding.Bindable;

import com.cod3vstudio.core.BR;
import com.cod3vstudio.core.model.entities.Product;
import com.cod3vstudio.core.model.responses.APIResponse;
import com.cod3vstudio.core.model.services.clouds.ServiceComponent;
import com.cod3vstudio.core.model.services.storages.ModelComponent;
import com.cod3vstudio.core.util.Constants;
import com.cod3vstudio.core.view.INavigator;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by quanghuymr403 on 22/09/2016.
 */
public class BiddingViewModel extends BaseViewModel {

    //region Properties

    private List<Product> mProducts;
    private ModelComponent mModelComponent;
    private ServiceComponent mServiceComponent;

    //endregion

    //region Constructors

    public BiddingViewModel(INavigator navigator, ModelComponent modelComponent, ServiceComponent serviceComponent) {
        super(navigator);
        mModelComponent = modelComponent;
        mServiceComponent = serviceComponent;
    }

    //endregion

    //region Getters and Setters

    @Bindable
    public List<Product> getProducts() {
        return mProducts;
    }

    public void setProducts(List<Product> products) {
        mProducts = products;
        notifyPropertyChanged(BR.products);
    }

    //endregion

    //region Lifecycle

    @Override
    public void onCreate() {
        super.onCreate();

        loadBiddingProducts();
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
    }

    //endregion

    //region Public methods

    public void loadBiddingProducts() {
        getNavigator().showBusyIndicator("Đang tải");
        mServiceComponent.getBiddingService().findByUser(getNavigator().getApplication().getSignedInUser().getId())
                .enqueue(new Callback<APIResponse<List<Product>>>() {
                    @Override
                    public void onResponse(Call<APIResponse<List<Product>>> call, Response<APIResponse<List<Product>>> response) {
                        if (response.isSuccessful() && response.body() != null & response.body().isSuccess()) {
                            setProducts(response.body().getData());
                        } else {
                            showMessage("Loi 1");
                        }
                        getNavigator().hideBusyIndicator();
                    }

                    @Override
                    public void onFailure(Call<APIResponse<List<Product>>> call, Throwable t) {
                        showMessage("Loi 2");
                        getNavigator().hideBusyIndicator();
                    }
                });
    }

    public void showProductDetailsCommand(Product product) {
        postSticky(product);
        getNavigator().navigateTo(Constants.PRODUCT_PAGE);

    }

    //endregion

}
