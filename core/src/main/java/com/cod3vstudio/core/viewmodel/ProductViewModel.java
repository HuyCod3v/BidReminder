package com.cod3vstudio.core.viewmodel;

import android.app.Activity;
import android.databinding.Bindable;

import com.cod3vstudio.core.BR;
import com.cod3vstudio.core.R;
import com.cod3vstudio.core.model.entities.Cart;
import com.cod3vstudio.core.model.entities.Change;
import com.cod3vstudio.core.model.entities.Product;
import com.cod3vstudio.core.model.entities.Saved;
import com.cod3vstudio.core.model.entities.User;
import com.cod3vstudio.core.model.responses.APIResponse;
import com.cod3vstudio.core.model.services.clouds.ServiceComponent;
import com.cod3vstudio.core.model.services.storages.ModelComponent;
import com.cod3vstudio.core.util.Constants;
import com.cod3vstudio.core.view.ICallback;
import com.cod3vstudio.core.view.INavigator;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

    public void setSaved(boolean isSaved) {
        if (!isSaved) {
            Saved saved = new Saved();
            saved.setItemId(mProduct.getItemId());
            saved.setRepositoryId(mProduct.getRepositoryId());
            saved.setName(mProduct.getName());
            saved.setCurrencyUnit(mProduct.getCurrencyUnit());
            saved.setPrice(mProduct.getPrice());
            saved.setDescription(mProduct.getDescription());
            saved.setImage(mProduct.getImage());

            mModelComponent.getSavedModel().add(saved);
        } else {
            mModelComponent.getSavedModel().delete(mProduct.getItemId(), mProduct.getRepositoryId());
        }
    }

    public boolean isSaved() {
        return mModelComponent.getSavedModel().find(mProduct.getItemId(), mProduct.getRepositoryId()) != null;
    }

    public void showBidPageCommand() {
        postSticky(mProduct);
        getNavigator().navigateTo(Constants.BID_PAGE);
    }

    public void buyProductCommand() {
        if (getNavigator().getApplication().getSignedInUser() == null) {
            getNavigator().navigateTo(Constants.SIGN_IN_PAGE);
            return;
        }

        Activity currentActivity = getCurrentActivity();
        String message = currentActivity.getString(R.string.do_you_want_to_buy)
                + " " + mProduct.getName()
                + " " + currentActivity.getString(R.string.for_price) + " "
                + mProduct.getPrice() + mProduct.getCurrencyUnit()
                + currentActivity.getString(R.string.contact_for_transaction);

        getNavigator().showMessage(currentActivity.getString(R.string.attention)
                , message
                , currentActivity.getString(R.string.yes)
                , currentActivity.getString(R.string.no)
                , new ICallback() {
                    @Override
                    public void onResult(Object result) {
                        boolean isAgreed = (boolean) result;
                        if (isAgreed) {
                            if (checkSignedInUserHasNecessaryContact()) {
                                buyProduct();
                            } else {
                                getNavigator().navigateTo(Constants.EDIT_PROFILE_PAGE);
                                showMessage(getCurrentActivity().getString(R.string.fill_in_information));
                            }
                        }
                    }

                    @Override
                    public void onFailure(Throwable t) {

                    }
                });
    }

    //endregion

    //region Private methods

    private void buyProduct() {
        mServiceComponent.getCartService().add(mProduct.getPrice()
                , getNavigator().getApplication().getSignedInUser().getId()
                , mProduct.getItemId()
                , mProduct.getRepositoryId()
                , mProduct.getName()
                , mProduct.getImage()
                , mProduct.getCurrencyUnit()
                , mProduct.getDescription()).enqueue(new Callback<APIResponse<Cart>>() {
            @Override
            public void onResponse(Call<APIResponse<Cart>> call, Response<APIResponse<Cart>> response) {
                if (response.isSuccessful() && response.body() != null && response.body().isSuccess()) {
                    showMessage(getCurrentActivity().getString(R.string.buy_successfully));
                } else {
                    showMessage(getCurrentActivity().getString(R.string.error_occurred));
                }
            }

            @Override
            public void onFailure(Call<APIResponse<Cart>> call, Throwable t) {
                showMessage(getCurrentActivity().getString(R.string.error_occurred));
            }
        });
    }

    private boolean checkSignedInUserHasNecessaryContact() {
        User signedInUser = getNavigator().getApplication().getSignedInUser();
        if (signedInUser.getEmail() != null && !signedInUser.getEmail().equals("")
                && signedInUser.getPhone() != null && !signedInUser.getPhone().equals("")
                && signedInUser.getAddress() != null && !signedInUser.getAddress().equals("")) {
            return true;
        } else {
            return false;
        }
    }

    //endregion

    //region Subscribe methods

    @Subscribe(sticky = true)
    public void event(Product product) {
        setProduct(product);
        getCurrentActivity().setTitle(mProduct.getName());
        unregister();
    }

    @Subscribe(sticky = true)
    public void event(Saved saved) {
        Product product = new Product();
        product.setImage(saved.getImage());
        product.setDescription(saved.getDescription());
        product.setCurrencyUnit(saved.getCurrencyUnit());
        product.setId(saved.getRepositoryId());
        product.setItemId(saved.getItemId());
        product.setPrice(saved.getPrice());
        product.setName(saved.getName());
        setProduct(product);
        getCurrentActivity().setTitle(mProduct.getName());
    }

    //endregion

}
