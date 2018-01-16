package com.kotlin.sample.persistence

import com.example.android.observability.persistence.User
import io.reactivex.Flowable

/**
 * Created by anna on 2017/12/13.
 */
interface UserDataSource {

    fun getUserById(id: String): Flowable<User>

    fun insertOrUpdateUser(user: User)

    fun deleteAllUsers()
}