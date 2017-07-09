package com.example.testdagg2;

import android.app.Application;

import com.example.testdagg2.dagger.AppComponent;
import com.example.testdagg2.dagger.AppModule;
import com.example.testdagg2.dagger.DaggerAppComponent;

/**
 * Created by zhangfang on 2017/6/26.
 */

public class MainApp extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();

    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
