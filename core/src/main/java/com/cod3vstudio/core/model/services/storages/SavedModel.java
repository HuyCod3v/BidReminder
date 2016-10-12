package com.cod3vstudio.core.model.services.storages;

import com.cod3vstudio.core.model.entities.Saved;

import java.util.List;

import io.realm.Realm;

/**
 * Created by HuyCod3v on 10/3/16.
 */

public class SavedModel implements ISavedModel {

    @Override
    public void add(Saved element) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();

        realm.copyToRealm(element);

        realm.commitTransaction();
    }

    @Override
    public void delete(String itemId, int repositoryId) {
        Realm realm = Realm.getDefaultInstance();
        Saved saved = realm.where(Saved.class).equalTo("itemId", itemId)
                .equalTo("repositoryId", repositoryId)
                .findFirst();

        if (hasResult(saved)) {
            realm.beginTransaction();

            saved.deleteFromRealm();

            realm.commitTransaction();
        }
    }

    @Override
    public Saved find(String itemId, int repositoryId) {
        Realm realm = Realm.getDefaultInstance();
        return realm.where(Saved.class).equalTo("itemId", itemId)
                .equalTo("repositoryId", repositoryId)
                .findFirst();
    }

    private boolean hasResult(Saved element) {
        if (element != null) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<Saved> findAll() {
        Realm realm = Realm.getDefaultInstance();
        return realm.where(Saved.class).findAll();
    }
}
