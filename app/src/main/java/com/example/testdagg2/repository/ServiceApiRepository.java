package com.example.testdagg2.repository;

import android.arch.lifecycle.LiveData;

import com.example.testdagg2.model.User;

/**
 * Created by zhangfang on 2017/6/27.
 */

public interface ServiceApiRepository {

    LiveData<User> getUser(String userName);

}
