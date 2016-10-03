package com.cod3vstudio.core.model.responses;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by Administrator on 7/30/2016.
 */
public class APIResponse<E> {

    //region Properties

    @SerializedName("success")
    private boolean success;

    @SerializedName("data")
    private E data;

    @SerializedName("message")
    private String message;

    //endregion

    //region Getters and Setters

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    //endregion

}
