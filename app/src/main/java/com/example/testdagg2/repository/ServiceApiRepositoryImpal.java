package com.example.testdagg2.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.example.testdagg2.ServiceAPI;
import com.example.testdagg2.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by zhangfang on 2017/6/27.
 */

public class ServiceApiRepositoryImpal implements ServiceApiRepository {

    private final ServiceAPI serviceAPI;


    public ServiceApiRepositoryImpal(ServiceAPI serviceAPI) {
        this.serviceAPI = serviceAPI;
    }

    @Override
    public LiveData<User> getUser(@NonNull String userName) {
        final MutableLiveData<User> liveData = new MutableLiveData<>();

        serviceAPI.user(userName).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                liveData.setValue(response.body());

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });

        return liveData;
    }
}
