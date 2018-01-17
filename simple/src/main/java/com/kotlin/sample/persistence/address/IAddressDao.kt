package com.kotlin.sample.persistence.address

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import io.reactivex.Flowable

/**
 * Created by anna on 17/01/2018.
 */
@Dao
interface IAddressDao {

    @Query("SELECT * FROM address WHERE address_id = :id")
    fun getAddressById(id: String): Flowable<Address>

    @Query("SELECT * FROM address")
    fun getAllAddress(): Flowable<Address>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAddress(address: Address)

    @Query("DELETE FROM address")
    fun deleteAllAddress()
}