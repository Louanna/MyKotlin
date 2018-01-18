package com.kotlin.sample.persistence

import android.arch.lifecycle.LiveData
import com.example.android.observability.persistence.User
import com.kotlin.sample.persistence.user.NameTuple
import com.kotlin.sample.persistence.user.UserAndBook
import io.reactivex.Flowable

/**
 * Created by anna on 2017/12/13.
 */
interface IUserDataSource {

    fun getUserById(id: String): Flowable<User>

    fun insertOrUpdateUser(user: User)

    fun deleteAllUsers()

    fun loadUserAndBook(): Flowable<List<UserAndBook>>

    fun loadUsersFromRegionsSync(firstName: List<String>): LiveData<List<NameTuple>>
}