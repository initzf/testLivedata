package com.example.testdagg2.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.example.testdagg2.model.User;
import com.example.testdagg2.repository.ServiceApiRepository;

import javax.inject.Inject;

/**
 * Created by zhangfang on 2017/6/27.
 */

public class UserViewModel extends ViewModel{
    private final ServiceApiRepository apiRepository;

    @Inject
    public UserViewModel(ServiceApiRepository apiRepository) {
        this.apiRepository = apiRepository;
    }


    public LiveData<User> getUser(String userName){
        return apiRepository.getUser(userName);
    }
}
