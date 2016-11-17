package com.cod3vstudio.core.model.services.storages;

import com.cod3vstudio.core.model.entities.Change;

import java.util.List;

/**
 * Created by Administrator on 16-Nov-16.
 */

public interface IChangeModel {

    void add(Change element);

    List<Change> getByProduct(int productId);

}
