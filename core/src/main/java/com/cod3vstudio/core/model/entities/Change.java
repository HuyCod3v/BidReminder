package com.cod3vstudio.core.model.entities;

import java.util.Date;

import io.realm.RealmObject;

/**
 * Created by quanghuymr403 on 25/09/2016.
 */
public class Change extends RealmObject {

    private double price;

    private Date changedAt;

    private int productId;

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getChangedAt() {
        return changedAt;
    }

    public void setChangedAt(Date changedAt) {
        this.changedAt = changedAt;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
}
