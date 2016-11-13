package com.cod3vstudio.core.model.services.clouds;

import com.cod3vstudio.core.model.entities.User;
import com.cod3vstudio.core.model.responses.APIResponse;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;


/**
 * Created by Administrator on 7/31/2016.
 */
public interface IUserService {

    @GET("/api/authenticate/sign-in")
    Call<APIResponse<User>> signIn(@Query("email") String email, @Query("password") String password);

    @GET("/api/authenticate/re-sign-in")
    Call<APIResponse<User>> reSignIn(@Query("remember_token") String rememberToken);

    @GET("/api/authenticate/sign-up")
    Call<APIResponse<Boolean>> signUp(@Query("email") String email, @Query("password") String password, @Query("name") String name, @Query("firebase_token") String firebaseToken);

    @FormUrlEncoded
    @PUT("/api/users/{id}")
    Call<APIResponse<User>> updateProfile(@Path("id") int id, @Field("email") String email, @Field("name") String name
                                        , @Field("phone") String phone, @Field("address") String address, @Field("nation") String nation);

    @FormUrlEncoded
    @PUT("/api/users/{id}")
    Call<APIResponse<User>> updatePassword(@Path("id") int id, @Field("password") String password);

    @FormUrlEncoded
    @PUT("/api/users/{id}")
    Call<APIResponse<User>> updateFirebaseToken(@Path("id") int id, @Field("firebase_token") String firebaseToken);

    @Multipart
    @POST("/api/users/upload-avatar/{id}")
    Call<APIResponse<Boolean>> uploadAvatar(@Part MultipartBody.Part file);
}
