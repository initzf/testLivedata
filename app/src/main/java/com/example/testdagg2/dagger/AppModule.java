package com.example.testdagg2.dagger;

import android.app.Application;

import com.example.testdagg2.ServiceAPI;
import com.example.testdagg2.repository.ServiceApiRepository;
import com.example.testdagg2.repository.ServiceApiRepositoryImpal;
import com.example.testdagg2.utils.LiveDataCallAdapterFactory;
import com.example.testdagg2.viewmodel.NewUserModel;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by zhangfang on 2017/6/26.
 */

@Module
public class AppModule {
    private Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Singleton
    @Provides
    public ServiceAPI providesServiceAPI() {
        return new Retrofit.Builder()
                .baseUrl(ServiceAPI.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(new LiveDataCallAdapterFactory())
                .build()
                .create(ServiceAPI.class);
    }

    @Singleton
    @Provides
    public ServiceApiRepository providesServiceApiRepository(ServiceAPI serviceAPI) {
        return new ServiceApiRepositoryImpal(serviceAPI);
    }

    @Singleton
    @Provides
    public NewUserModel providesNewUserModel(ServiceAPI serviceAPI){
        return new NewUserModel(serviceAPI);
    }
}
