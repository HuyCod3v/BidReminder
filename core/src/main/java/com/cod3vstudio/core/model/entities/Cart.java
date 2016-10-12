package com.cod3vstudio.core.model.entities;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 10/8/2016.
 */
public class Cart {

    @SerializedName("id")
    private int id;

    @SerializedName("product_id")
    private int productId;

    @SerializedName("buy_price")
    private double buyPrice;

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

    public double getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(double buyPrice) {
        this.buyPrice = buyPrice;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
