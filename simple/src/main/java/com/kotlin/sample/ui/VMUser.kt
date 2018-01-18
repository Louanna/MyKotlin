package com.example.android.observability.ui

import android.arch.lifecycle.ViewModel
import com.example.android.observability.persistence.User
import com.kotlin.sample.persistence.IUserDataSource
import com.kotlin.sample.persistence.address.Address
import com.kotlin.sample.persistence.user.UserAndBook
import io.reactivex.Completable
import io.reactivex.Flowable

/**
 * Created by anna on 2017/12/13.
 */
class VMUser(private val dataSource: IUserDataSource) : ViewModel() {

    fun getUserName(): Flowable<String> {
        return dataSource.getUserById(USER_ID)
                .map { user ->
                    (user.userName + user.createTime)
                }
    }

    fun updateUserName(userName: String): Completable {
        return Completable.fromAction {
            val user = User(USER_ID, userName)
            val address = Address(ADDRESS_ID, "street1", "state1", "city1", "215000")
            address.userId = USER_ID
            user.address = address
            dataSource.insertOrUpdateUser(user)
        }
    }

    fun getUser(): Flowable<User> {
        return dataSource.getUserById("1")
    }

    fun loadUserAndBook(): Flowable<List<UserAndBook>> {
        return dataSource.loadUserAndBook()
    }

    companion object {
        const val USER_ID = "1"
        const val ADDRESS_ID = "1"
    }
}
