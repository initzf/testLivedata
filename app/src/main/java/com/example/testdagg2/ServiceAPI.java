package com.example.testdagg2;

import android.arch.lifecycle.LiveData;

import com.example.testdagg2.model.Repo;
import com.example.testdagg2.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by zhangfang on 2017/6/26.
 */

public interface ServiceAPI {

    String URL = "https://api.github.com/";

    @GET("/users/{user}/repos")
    Call<List<Repo>> listRepos(@Path("user") String user);

    @GET("/users/{user}")
    Call<User> user(@Path("user") String user);


    @GET("/users/{user}")
    LiveData<ApiResponse<User>> getUser(@Path("user") String user);



    @GET("/users/{user}/repos")
    LiveData<ApiResponse<List<Repo>>> getListRepos(@Path("user") String user);
}
