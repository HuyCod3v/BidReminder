package com.cod3vstudio.core.viewmodel;

import android.databinding.Bindable;

import com.cod3vstudio.core.BR;
import com.cod3vstudio.core.R;
import com.cod3vstudio.core.model.entities.Page;
import com.cod3vstudio.core.model.entities.Product;
import com.cod3vstudio.core.model.responses.APIResponse;
import com.cod3vstudio.core.model.services.clouds.ServiceComponent;
import com.cod3vstudio.core.model.services.storages.ModelComponent;
import com.cod3vstudio.core.util.Configuration;
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
public class HomeViewModel extends BaseViewModel {

    //region Properties

    private List<Product> mProducts;
    private ModelComponent mModelComponent;
    private ServiceComponent mServiceComponent;

    //endregion

    //region Constructors

    public HomeViewModel(INavigator navigator, ModelComponent modelComponent, ServiceComponent serviceComponent) {
        super(navigator);
        mModelComponent = modelComponent;
        mServiceComponent = serviceComponent;
    }

    //endregion

    //region Getters and Setters


    @Override
    public INavigator getNavigator() {
        return super.getNavigator();
    }

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
        loadPromotion(Configuration.ITEMS_PER_PAGE, 1);
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
        mProducts = null;
    }

    //endregion

    //region Public methods

    public void loadProducts(final String name, int limit, final int offset) {
        mServiceComponent.getProductService().search(name, limit, offset).enqueue(new Callback<APIResponse<List<Product>>>() {
            @Override
            public void onResponse(Call<APIResponse<List<Product>>> call, Response<APIResponse<List<Product>>> response) {
                if (response.isSuccessful() && response.body() != null && response.body().isSuccess()) {
                    if (offset == 1) {
                        setProducts(response.body().getData());
                    } else if (offset > 1) {
                        mProducts.addAll(response.body().getData());
                        notifyPropertyChanged(BR.products);
                    }

                } else {
                    retry(offset, name);
                }
            }

            @Override
            public void onFailure(Call<APIResponse<List<Product>>> call, Throwable t) {
                retry(offset, name);
            }
        });
    }

    public void loadPromotion(int limit, final int offset) {

        mServiceComponent.getProductService().getPromotion(limit, offset).enqueue(new Callback<APIResponse<List<Product>>>() {
            @Override
            public void onResponse(Call<APIResponse<List<Product>>> call, Response<APIResponse<List<Product>>> response) {
                if (response.isSuccessful() && response.body() != null && response.body().isSuccess()) {
                    if (offset == 1) {
                        setProducts(response.body().getData());
                    } else if (offset > 1) {
                        mProducts.addAll(response.body().getData());
                        notifyPropertyChanged(BR.products);
                    }

                } else {
                    retry(offset, null);
                }
            }

            @Override
            public void onFailure(Call<APIResponse<List<Product>>> call, Throwable t) {
                retry(offset, null);
            }
        });
    }

    public void showProductDetailsCommand(Product product) {
        getNavigator().navigateTo(Constants.PRODUCT_PAGE);
        postSticky(product);
    }

    //endregion

    private void retry(int pageNumber, String name) {
        post(new Page(pageNumber, name, getCurrentActivity().getString(R.string.error_occurred), "TRY"));
    }

}
