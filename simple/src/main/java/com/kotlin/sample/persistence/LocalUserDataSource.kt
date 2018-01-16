package com.kotlin.sample.persistence

import com.example.android.observability.persistence.User
import com.example.android.observability.persistence.UserDao
import io.reactivex.Flowable

/**
 * Created by anna on 2017/12/13.
 */
class LocalUserDataSource : UserDataSource {

    private var mUserDao: UserDao

    constructor(mUserDao: UserDao) {
        this.mUserDao = mUserDao
    }

    override fun getUserById(id: String): Flowable<User> {
        return mUserDao.getUserById(id)
    }

    override fun insertOrUpdateUser(user: User) {
        mUserDao.insertUser(user)
    }

    override fun deleteAllUsers() {
        mUserDao.deleteAllUsers()
    }
}