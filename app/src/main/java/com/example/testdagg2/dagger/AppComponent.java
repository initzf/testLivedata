package com.example.testdagg2.dagger;

import android.content.Context;
import android.support.annotation.NonNull;

import com.example.testdagg2.MainActivity;
import com.example.testdagg2.MainApp;
import com.example.testdagg2.TestFragment;
import com.example.testdagg2.TestMe;

import javax.inject.Singleton;

import dagger.Component;


/**
 * Created by zhangfang on 2017/6/26.
 */

@Component(modules = AppModule.class)
@Singleton
public abstract class AppComponent {
    public static AppComponent from(@NonNull Context context) {
        return ((MainApp) context.getApplicationContext()).getAppComponent();
    }

    public abstract void inject(MainActivity mainActivity);

    public abstract void inject(TestFragment fragment);

    public abstract void inject(TestMe fragment);
}
