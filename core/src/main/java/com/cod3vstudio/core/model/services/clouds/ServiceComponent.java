package com.cod3vstudio.core.model.services.clouds;

/**
 * Created by quanghuymr403 on 24/09/2016.
 */
public class ServiceComponent {

    private final IUserService mUserService;

    public ServiceComponent(IUserService userService) {
        mUserService = userService;
    }

    public IUserService getUserService() {
        return mUserService;
    }
}
