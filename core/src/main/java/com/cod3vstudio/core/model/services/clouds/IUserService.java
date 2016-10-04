package com.cod3vstudio.core.model.services.clouds;

import com.cod3vstudio.core.model.entities.User;
import com.cod3vstudio.core.model.responses.APIResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;


/**
 * Created by Administrator on 7/31/2016.
 */
public interface IUserService {

    @GET("/api/authenticate/sign-in")
    Call<APIResponse<User>> signIn(@Query("email") String email, @Query("password") String password);

    @GET("/api/authenticate/sign-up")
    Call<APIResponse<Boolean>> signUp(@Query("email") String email, @Query("password") String password, @Query("name") String name);

}
