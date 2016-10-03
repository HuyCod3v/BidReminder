package com.cod3vstudio.core.model.entities;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 7/31/2016.
 */
public class User {

    //region Properties

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("password")
    private String password;

    @SerializedName("email")
    private String email;

    //endregion

    //region Getter and Setter

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    //endregion

}
