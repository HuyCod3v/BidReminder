package com.cod3vstudio.core.model.entities;

import java.util.Date;

/**
 * Created by quanghuymr403 on 25/09/2016.
 */
public class Change {

    private int id;

    private double price;

    private Date changedAt;

    private int productId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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