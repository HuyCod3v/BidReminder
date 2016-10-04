package com.cod3vstudio.core.viewmodel;

import android.databinding.Bindable;

import com.cod3vstudio.core.BR;
import com.cod3vstudio.core.model.entities.Product;
import com.cod3vstudio.core.util.Constants;
import com.cod3vstudio.core.view.INavigator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by quanghuymr403 on 22/09/2016.
 */
public class HomeViewModel extends BaseViewModel {

    //region Properties

    private List<Product> mProducts;

    //endregion

    //region Constructors

    public HomeViewModel(INavigator navigator) {
        super(navigator);
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

        loadSavedProducts();
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

    public void loadSavedProducts() {
        List<Product> products = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Product product = new Product();
            product.setId(i);
            product.setName("Name " + i);
            product.setPrice(i);
            product.setCurrencyUnit("VND");
            products.add(product);
        }

        setProducts(products);
    }

    public void showProductDetailsCommand(Product product) {
        postSticky(product);
        getNavigator().navigateTo(Constants.PRODUCT_PAGE);
    }

    //endregion

}
