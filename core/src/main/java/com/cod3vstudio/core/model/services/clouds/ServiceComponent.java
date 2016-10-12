package com.cod3vstudio.core.model.services.clouds;

/**
 * Created by quanghuymr403 on 24/09/2016.
 */
public class ServiceComponent {

    private final IUserService mUserService;
    private final IProductService mProductService;
    private final IBiddingService mBiddingService;
    private final ICartService mCartService;

    public ServiceComponent(IUserService userService, IProductService productService, IBiddingService biddingService, ICartService cartService) {
        mUserService = userService;
        mProductService = productService;
        mBiddingService = biddingService;
        mCartService = cartService;
    }

    public IUserService getUserService() {
        return mUserService;
    }
    public IProductService getProductService() { return mProductService; }
    public IBiddingService getBiddingService() {
        return mBiddingService;
    }
    public ICartService getCartService() {
        return mCartService;
    }
}
