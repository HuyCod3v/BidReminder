package com.cod3vstudio.core.model.services.storages;

import com.cod3vstudio.core.model.entities.Saved;

import java.util.List;

/**
 * Created by tranvantuat on 10/3/16.
 */
public interface ISavedModel {

    void add(Saved element);

    void delete(int id);

    void update(Saved element);

    Saved find(int id);

    List<Saved> findAll();

}
