package com.example.android.observability.ui

import android.arch.lifecycle.ViewModel
import com.example.android.observability.persistence.User
import com.kotlin.sample.persistence.UserDataSource
import io.reactivex.Completable
import io.reactivex.Flowable

/**
 * Created by anna on 2017/12/13.
 */
class UserViewModel(private val dataSource: UserDataSource) : ViewModel() {

    fun getUserName(): Flowable<String> {
        return dataSource.getUserById(USER_ID)
                .map { user ->
                    (user.userName + user.createTime)
                }
    }

    fun updateUserName(userName: String): Completable {
        return Completable.fromAction {
            val user = User(USER_ID, userName)
            dataSource.insertOrUpdateUser(user)
        }
    }

    companion object {
        const val USER_ID = "1"
    }
}
