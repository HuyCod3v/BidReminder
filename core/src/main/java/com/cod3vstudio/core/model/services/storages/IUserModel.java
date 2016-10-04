package com.cod3vstudio.core.model.services.storages;

import com.cod3vstudio.core.model.entities.User;

/**
 * Created by quanghuymr403 on 24/09/2016.
 */
public interface IUserModel {

    void add(User user);
    void update(User user);
    void delete(int id);
    User find(String email, String password);

}

