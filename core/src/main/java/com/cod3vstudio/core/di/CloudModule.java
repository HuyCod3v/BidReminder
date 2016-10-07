package com.cod3vstudio.core.di;

import com.cod3vstudio.core.model.services.clouds.IBiddingService;
import com.cod3vstudio.core.model.services.clouds.IProductService;
import com.cod3vstudio.core.model.services.clouds.IUserService;
import com.cod3vstudio.core.model.services.clouds.ServiceComponent;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.cod3vstudio.core.util.Configuration;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 7/26/2016.
 */

@Module(includes = { AppModule.class })
public class CloudModule {

    //region Provide methods

    @Provides
    @Singleton
    public IUserService providesUserService() {
        Gson gson = createGson();
        OkHttpClient okHttpClient = createOkHttpClient();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Configuration.BID_REMINDER_API_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build();

        return retrofit.create(IUserService.class);
    }

    @Provides
    @Singleton
    public IProductService providesProductService() {
        Gson gson = createGson();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Configuration.BID_REMINDER_API_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return retrofit.create(IProductService.class);
    }

    @Provides
    @Singleton
    public IBiddingService providesBiddingService() {
        Gson gson = createGson();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Configuration.BID_REMINDER_API_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return retrofit.create(IBiddingService.class);
    }


    @Provides
    @Singleton
    public ServiceComponent providesServiceComponent(IUserService userService, IProductService productService, IBiddingService biddingService) {
        return new ServiceComponent(userService, productService, biddingService);
    }

    //endregion

    //region Private methods

    private Gson createGson() {
        return new GsonBuilder().setLenient()
                                .setDateFormat(Configuration.TIMESTAMP_FORMAT)
                                .create();
    }

    private OkHttpClient createOkHttpClient() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .build();
        return okHttpClient;
    }

    //endregion
}
