package com.ac.demo;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anna on 2017/12/7.
 */

public class MyViewModel extends ViewModel {

    private MutableLiveData<List<User>> users;
    public MutableLiveData<List<User>> getUsers() {
        if (users == null) {
            users = new MutableLiveData<>();
            loadUsers();
        }
        return users;
    }

    private void loadUsers() {
        // Do an asyncronous operation to fetch users.

        User user = new User();
        user.setFirstName("ABC");
        user.setId(1);

        List<User> userList = new ArrayList<>();
        userList.add(user);

        users.setValue(userList);
    }

}
