package com.ac.demo;

import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;

/**
 * Created by anna on 2017/12/7.
 */

public class MyDatabase extends AppDatabase {

    @Override
    public UserDao userDao() {
        return new MyUserDao();
    }

    @Override
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration config) {
        return null;
    }

    @Override
    protected InvalidationTracker createInvalidationTracker() {
        return null;
    }
}
