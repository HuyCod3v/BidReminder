package com.cod3vstudio.core.model.services.storages;

import com.cod3vstudio.core.model.entities.Change;

import java.util.List;

import io.realm.Realm;

/**
 * Created by Administrator on 16-Nov-16.
 */

public class ChangeModel implements IChangeModel {

    @Override
    public void add(Change element) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();

        realm.copyToRealm(element);

        realm.commitTransaction();
    }

    @Override
    public List<Change> getByProduct(int productId) {
        Realm realm = Realm.getDefaultInstance();
        return realm.where(Change.class).equalTo("productId", productId)
                .findAll();
    }
}
