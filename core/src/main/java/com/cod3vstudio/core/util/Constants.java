package com.cod3vstudio.core.util;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Administrator on 7/25/2016.
 */

public class Constants {

    //region Constructors

    private Constants() {

    }

    //endregion

    //region Pages

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({
            MAIN_PAGE, SIGN_IN_PAGE, SIGN_UP_PAGE, PRODUCT_PAGE, PROFILE_PAGE, EDIT_PROFILE_PAGE, EDIT_PASSWORD_PAGE, SETTINGS_PAGE, FILTER_PAGE, BID_PAGE
    })
    public @interface PageKey {}

    public static final int MAIN_PAGE = 0;
    public static final int SIGN_IN_PAGE = 1;
    public static final int SIGN_UP_PAGE = 2;
    public static final int PRODUCT_PAGE = 3;
    public static final int PROFILE_PAGE = 4;
    public static final int EDIT_PROFILE_PAGE = 5;
    public static final int EDIT_PASSWORD_PAGE = 6;
    public static final int SETTINGS_PAGE = 7;
    public static final int FILTER_PAGE = 8;
    public static final int BID_PAGE = 9;

    public static final int PICK_IMAGE_COMMAND = 1;

    //endregion

}
