package com.ac.demo;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anna on 2017/11/30.
 */

public class NameActivity extends AppCompatActivity {

    private TextView mNameTextView;
    private Button mButton1, mButton2, mButton3;

    private NameViewModel mModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.setContentView(R.layout.activity_main);

        mNameTextView = findViewById(R.id.tv);
        mButton1 = findViewById(R.id.bt_1);
        mButton2 = findViewById(R.id.bt_2);
        mButton3 = findViewById(R.id.bt_3);

        // Other code to setup the activity...

//        // Get the ViewModel.
//        mModel = ViewModelProviders.of(this).get(NameViewModel.class);
//
//        // Create the observer which updates the UI.
//        final Observer<String> nameObserver = (newName) -> {
//            // Update the UI, in this case, a TextView.
//            mNameTextView.setText(newName);
//        };
//
//        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
//        mModel.getCurrentName().observe(this, nameObserver);

        MyViewModel model = ViewModelProviders.of(this).get(MyViewModel.class);
        model.getUsers().observe(this, users -> {
            // update UI
            mNameTextView.setText(users.get(0).getFirstName());
        });

        mButton1.setOnClickListener((v) -> {
            model.getUsers().setValue(loadUsers("John1"));
        });

        mButton2.setOnClickListener((v) -> {
            model.getUsers().setValue(loadUsers("John2"));
        });

        mButton3.setOnClickListener((v) -> {
            model.getUsers().setValue(loadUsers("John3"));
        });
    }

    private List<User> loadUsers(String name) {
        // Do an asyncronous operation to fetch users.
        User user = new User();
        user.setFirstName(name);
        user.setId(1);

        List<User> userList = new ArrayList<>();
        userList.add(user);

        return userList;
    }
}
