package com.cod3vstudio.core.model.entities;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 10/6/2016.
 */
public class Bidding {

    @SerializedName("id")
    private int id;

    @SerializedName("product_id")
    private int productId;

    @SerializedName("repository_id")
    private int repositoryId;

    @SerializedName("bid_price")
    private double bidPrice;

    @SerializedName("last_price")
    private double lastPrice;

    @SerializedName("is_buy_automatically")
    private boolean isBuyAutomatically;

    @SerializedName("user_id")
    private int userId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getRepositoryId() {
        return repositoryId;
    }

    public void setRepositoryId(int repositoryId) {
        this.repositoryId = repositoryId;
    }

    public double getBidPrice() {
        return bidPrice;
    }

    public void setBidPrice(double bidPrice) {
        this.bidPrice = bidPrice;
    }

    public double getLastPrice() {
        return lastPrice;
    }

    public void setLastPrice(double lastPrice) {
        this.lastPrice = lastPrice;
    }

    public boolean getIsBuyAutomatically() {
        return isBuyAutomatically;
    }

    public void setIsBuyAutomatically(boolean isBuyAutomatically) {
        this.isBuyAutomatically = isBuyAutomatically;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
