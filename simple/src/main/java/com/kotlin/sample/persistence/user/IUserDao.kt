package com.example.android.observability.persistence

import android.arch.persistence.room.*

import io.reactivex.Flowable
import android.arch.persistence.room.Update
import com.kotlin.sample.persistence.user.NameTuple
import android.arch.lifecycle.LiveData

/**
 * Created by anna on 2017/12/13.
 */
@Dao
interface IUserDao {

    @Query("SELECT * FROM users WHERE id = :id")
    fun getUserById(id: String): Flowable<User>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: User)

    @Query("DELETE FROM users WHERE id = :id")
    fun deleteUserById(id: String)

    @Delete
    fun deleteAllUsers(vararg users: User)

    @Update
    fun updateUsers(vararg users: User)

    @Query("SELECT * FROM users WHERE id IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<User>

    @Query("SELECT * FROM users WHERE first_name LIKE :first AND " + "last_name LIKE :last LIMIT 1")
    fun findByName(first: String, last: String): User

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg users: User)

    @Query("SELECT first_name, last_name FROM users")
    fun loadFullName(): List<NameTuple>

    @Query("SELECT first_name, last_name FROM users WHERE first_name IN (:firstName)")
    fun loadUsersFromRegionsSync(firstName: List<String>): LiveData<List<NameTuple>>

}
