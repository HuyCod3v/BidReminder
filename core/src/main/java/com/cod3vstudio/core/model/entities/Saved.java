package com.cod3vstudio.core.model.entities;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by tranvantuat on 10/3/16.
 */
public class Saved extends RealmObject {

    //region Properties

    private String itemId;

    private int repositoryId;

    private String name;

    private String image;

    private double price;

    private String currencyUnit;

    private String description;

    //endregion

    //region Getters and Setters

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public int getRepositoryId() {
        return repositoryId;
    }

    public void setRepositoryId(int repositoryId) {
        this.repositoryId = repositoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCurrencyUnit() {
        return currencyUnit;
    }

    public void setCurrencyUnit(String currencyUnit) {
        this.currencyUnit = currencyUnit;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    //endregion
}
