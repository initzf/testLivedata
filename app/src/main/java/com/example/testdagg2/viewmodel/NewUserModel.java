package com.example.testdagg2.viewmodel;

import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import com.example.testdagg2.ApiResponse;
import com.example.testdagg2.ServiceAPI;
import com.example.testdagg2.model.Repo;
import com.example.testdagg2.model.User;

import java.util.List;

/**
 * Created by zhangfang on 2017/7/1.
 */

public class NewUserModel extends ViewModel {
    private ServiceAPI serviceAPI;


    public NewUserModel(ServiceAPI serviceAPI) {
        this.serviceAPI = serviceAPI;
    }

    public LiveData<ApiResponse<User>> getNewUser(String name) {
        return serviceAPI.getUser(name);
    }


    public LiveData<User> getNewUser2(String name) {
        LiveData<User> liveData = Transformations.map(serviceAPI.getUser(name), new Function<ApiResponse<User>, User>() {
            @Override
            public User apply(ApiResponse<User> input) {

                if (input.code == 200) {
                    return input.body;
                }
                return null;
            }
        });

        return liveData;
    }


    public LiveData<List<Repo>> getList(@NonNull final LiveData<User> liveDataUser) {
        return Transformations.switchMap(liveDataUser, new Function<User, LiveData<List<Repo>>>() {
            @Override
            public LiveData<List<Repo>> apply(User input) {

                return Transformations.map(serviceAPI.getListRepos(input.getLogin()), new Function<ApiResponse<List<Repo>>, List<Repo>>() {
                    @Override
                    public List<Repo> apply(ApiResponse<List<Repo>> input) {
                        if (input.code == 200) {
                            return input.body;
                        }
                        return null;
                    }
                });
            }
        });

    }
}
