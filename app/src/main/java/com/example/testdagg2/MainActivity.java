package com.example.testdagg2;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.arch.lifecycle.LifecycleActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.example.testdagg2.dagger.AppComponent;
import com.example.testdagg2.model.User;
import com.example.testdagg2.viewmodel.NewUserModel;
import com.example.testdagg2.viewmodel.UserViewModel;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends LifecycleActivity {

    private static final String TAG = "MainActivity";

    @Inject
    UserViewModel viewModel;


    @Inject
    NewUserModel userModel;

    @Inject
    ServiceAPI serviceAPI;

    private TestMe testMe;

    private int tmHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppComponent.from(this).inject(this);

        testMe = (TestMe) findViewById(R.id.testme);

        testMe.setLifecycle(getLifecycle());

        //getSupportFragmentManager().beginTransaction().add(R.id.fragment, TestFragment.newInstance()).commit();

        if (serviceAPI == null) {
            Log.i(TAG, "onCreate: " + (true));
            return;
        }
        serviceAPI.user("initzf").enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Log.i(TAG, "onResponse: " + response.body().toString());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });


        /*userModel.getNewUser2("initzf").observe(this, new Observer<User>() {
            @Override
            public void onChanged(@Nullable User user) {
                if (user == null) {
                    return;
                }
                Log.i(TAG, "onChanged: zf=" + user.toString());
            }
        });*/

       /* userModel.getList(userModel.getNewUser2("initzf")).observe(this, new Observer<List<Repo>>() {
            @Override
            public void onChanged(@Nullable List<Repo> repos) {
                if (repos == null) {
                    return;
                }

                Log.i(TAG, "onChanged: " + repos.toString());
            }
        });*/


        float mDensity = getResources().getDisplayMetrics().density;
        tmHeight = (int) (mDensity * 400 + 0.5);

        Log.i(TAG, "onCreate: " + tmHeight);
    }


    public void tagg(View view) {
        if (testMe.getVisibility() == View.VISIBLE) {
            close(testMe);
        } else {
            open(testMe);
        }
    }


    private void open(final View view) {

        view.setVisibility(View.VISIBLE);

        Log.i(TAG, "open: " + view.getMeasuredHeight());


        ValueAnimator animator = createAnimator(view, 0, tmHeight);
        animator.start();
    }

    private void close(final View view) {
        int height = view.getHeight();
        Log.i(TAG, "close: " + height);
        ValueAnimator animator = createAnimator(view, height, 0);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                view.setVisibility(View.GONE);
            }
        });

        animator.start();

    }


    private ValueAnimator createAnimator(final View view, int start, int end) {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(start, end);

        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (int) animation.getAnimatedValue();
                Log.i(TAG, "onAnimationUpdate: " + value);
                ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                layoutParams.height = value;
                view.setLayoutParams(layoutParams);
            }
        });

        return valueAnimator;
    }
}
