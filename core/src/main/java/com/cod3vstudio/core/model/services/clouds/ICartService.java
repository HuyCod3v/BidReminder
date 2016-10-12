package com.cod3vstudio.core.model.services.clouds;

import com.cod3vstudio.core.model.entities.Bidding;
import com.cod3vstudio.core.model.entities.Cart;
import com.cod3vstudio.core.model.entities.Product;
import com.cod3vstudio.core.model.responses.APIResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Administrator on 10/8/2016.
 */
public interface ICartService {

    @GET("/api/carts")
    Call<APIResponse<List<Product>>> findByUser(@Query("user_id") int userId);

    @FormUrlEncoded
    @POST("/api/carts")
    Call<APIResponse<Cart>> add(@Field("buy_price") double buyPrice
            , @Field("user_id") int userId
            , @Field("item_id") String itemId
            , @Field("repository_id") int repositoryId
            , @Field("name") String name
            , @Field("image") String image
            , @Field("currency_unit") String currencyUnit
            , @Field("description") String description);
}
