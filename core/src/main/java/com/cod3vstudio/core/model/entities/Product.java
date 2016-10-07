package com.cod3vstudio.core.model.entities;

import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;

/**
 * Created by quanghuymr403 on 22/09/2016.
 */
public class Product {

    //region Properties

    @SerializedName("id")
    private int id;

    @SerializedName("item_id")
    private String itemId;

    @SerializedName("name")
    private String name;

    @SerializedName("image")
    private String image;

    @SerializedName("price")
    private double price;

    @SerializedName("repository_id")
    private int repositoryId;

    @SerializedName("currency_unit")
    private String currencyUnit;

    @SerializedName("description")
    private String description;

    //endregion

    //region Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getRepositoryId() {
        return repositoryId;
    }

    public void setRepositoryId(int repositoryId) {
        this.repositoryId = repositoryId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    //endregion


}
