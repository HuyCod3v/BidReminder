package com.cod3vstudio.core.model.services.storages;

import com.cod3vstudio.core.model.entities.User;

import io.realm.Realm;

/**
 * Created by Administrator on 7/31/2016.
 */
public class UserModel extends BaseModel implements IUserModel {

    @Override
    public void add(User user) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.copyToRealm(user);
        realm.commitTransaction();
    }

    @Override
    public void update(User user) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(user);
        realm.commitTransaction();
    }

    @Override
    public void delete(int id) {
        Realm realm = Realm.getDefaultInstance();
        User user = realm.where(User.class).equalTo("id", id).findFirst();
        if (hasResult(user)) {
            realm.beginTransaction();
            user.deleteFromRealm();
            realm.commitTransaction();
        }
    }

    @Override
    public User find(String email, String password) {
        Realm realm = Realm.getDefaultInstance();
        return realm.where(User.class).equalTo("email", email)
                                           .equalTo("password", password)
                                           .findFirst();
    }

    private boolean hasResult(User user) {
        if (user != null) {
            return true;
        } else {
            return false;
        }
    }
}
