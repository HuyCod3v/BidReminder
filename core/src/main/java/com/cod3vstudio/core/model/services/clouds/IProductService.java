package com.cod3vstudio.core.model.services.clouds;

import com.cod3vstudio.core.model.entities.Product;
import com.cod3vstudio.core.model.responses.APIResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Administrator on 10/5/2016.
 */
public interface IProductService {

    @GET("/api/products")
    Call<APIResponse<List<Product>>> search(@Query("name") String name, @Query("limit") long limit, @Query("offset") long offset);
}
