package com.kotlin.sample.persistence

import com.example.android.observability.persistence.User
import com.example.android.observability.persistence.IUserDao
import com.kotlin.sample.persistence.user.UserAndBook
import io.reactivex.Flowable

/**
 * Created by anna on 2017/12/13.
 */
class UserDataSource : IUserDataSource {

    private var mIUserDao: IUserDao

    constructor(userDao: IUserDao) {
        this.mIUserDao = userDao
    }

    override fun getUserById(id: String): Flowable<User> {
        return mIUserDao.getUserById(id)
    }

    override fun insertOrUpdateUser(user: User) {
        mIUserDao.insertUser(user)
    }

    override fun deleteAllUsers() {
        mIUserDao.deleteAllUsers()
    }

    override fun loadUserAndBook(): Flowable<List<UserAndBook>> {
        return mIUserDao.loadUserAndBook()
    }
}