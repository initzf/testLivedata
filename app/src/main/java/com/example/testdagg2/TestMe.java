package com.example.testdagg2;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.OnLifecycleEvent;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.LinearLayout;

import com.example.testdagg2.dagger.AppComponent;
import com.example.testdagg2.model.User;
import com.example.testdagg2.viewmodel.NewUserModel;

import javax.inject.Inject;

/**
 * Created by zhangfang on 2017/7/4.
 */

public class TestMe extends LinearLayout implements LifecycleObserver {


    private static final String TAG = "TestMe";

    private Lifecycle lifecycle;

    @Inject
    NewUserModel userModel;

    public TestMe(Context context) {
        this(context, null);
    }

    public TestMe(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TestMe(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);


        Log.i(TAG, "TestMe: ");

        AppComponent.from(context).inject(this);

        //init();
    }

    public void setLifecycle(Lifecycle lifecycle) {
        lifecycle.addObserver(this);
        this.lifecycle = lifecycle;
    }

    private void init() {

        userModel.getNewUser2("initzf").observeForever(new Observer<User>() {
            @Override
            public void onChanged(@Nullable User user) {
                Log.i(TAG, "onChanged: " + user.toString());
            }
        });
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void resume(){
        Log.i(TAG, "resume: ");
        //init();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onPause(){
        Log.i(TAG, "onPause: ");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onStop(){
        Log.i(TAG, "onStop: ");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void OnDestroy(){
        Log.i(TAG, "OnDestroy: ");
    }

}
