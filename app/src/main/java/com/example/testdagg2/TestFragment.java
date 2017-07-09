package com.example.testdagg2;

import android.arch.lifecycle.LifecycleFragment;
import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.testdagg2.dagger.AppComponent;
import com.example.testdagg2.model.User;
import com.example.testdagg2.viewmodel.NewUserModel;

import javax.inject.Inject;

/**
 * Created by zhangfang on 2017/7/2.
 */

public class TestFragment extends LifecycleFragment {
    private static final String TAG = "TestFragment";
    @Inject
    NewUserModel userModel;


    public static TestFragment newInstance() {

        Bundle args = new Bundle();

        TestFragment fragment = new TestFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return new TextView(getContext());
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        AppComponent.from(getContext()).inject(this);


        final TextView view1 = (TextView) view;

        view1.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
        view1.setTextColor(ContextCompat.getColor(getContext(), R.color.colorPrimaryDark));

        userModel.getNewUser2("initzf").observe(this, new Observer<User>() {
            @Override
            public void onChanged(@Nullable User user) {
                if (user == null) {
                    return;
                }

                view1.setText(user.getLogin());

                Log.i(TAG, "onChanged: " + user.toString());
            }
        });

    }
}
