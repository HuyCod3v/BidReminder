package com.cod3vstudio.core.model.services.storages;

import com.cod3vstudio.core.model.entities.Saved;

import java.util.List;

/**
 * Created by tranvantuat on 10/3/16.
 */
public interface ISavedModel {

    void add(Saved element);

    void delete(String itemId, int repositoryId);

    Saved find(String itemId, int repositoryId);

    List<Saved> findAll();

}
