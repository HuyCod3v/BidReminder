package com.cod3vstudio.core.di;

import com.cod3vstudio.core.model.services.clouds.IUserService;
import com.cod3vstudio.core.model.services.clouds.ServiceComponent;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.cod3vstudio.core.util.Configuration;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
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

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Configuration.BID_REMINDER_API_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return retrofit.create(IUserService.class);
    }

    @Provides
    @Singleton
    public ServiceComponent providesServiceComponent(IUserService userService) {
        return new ServiceComponent(userService);
    }


    //endregion

    //region Private methods

    private Gson createGson() {
        return new GsonBuilder().setLenient()
                                .setDateFormat(Configuration.TIMESTAMP_FORMAT)
                                .create();
    }

    //endregion
}
