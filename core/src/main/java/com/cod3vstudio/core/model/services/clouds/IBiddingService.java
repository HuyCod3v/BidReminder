package com.cod3vstudio.core.model.services.clouds;

import com.cod3vstudio.core.model.entities.Bidding;
import com.cod3vstudio.core.model.entities.Product;
import com.cod3vstudio.core.model.responses.APIResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Administrator on 10/6/2016.
 */
public interface IBiddingService {

    @GET("/api/biddings")
    Call<APIResponse<List<Product>>> findByUser(@Query("user_id") int userId);

    @FormUrlEncoded
    @POST("/api/biddings")
    Call<APIResponse<Bidding>> add(@Field("bid_price") double bidPrice
            , @Field("last_price") double lastPrice
            , @Field("is_buy_automatically") int isBuyAutomatically
            , @Field("user_id") int userId
            , @Field("item_id") String itemId
            , @Field("repository_id") int repositoryId
            , @Field("name") String name
            , @Field("image") String image
            , @Field("currency_unit") String currencyUnit
            , @Field("description") String description);

    @DELETE("/api/biddings/{id}")
    Call<APIResponse<Integer>> delete(@Path("id") int id);
}
