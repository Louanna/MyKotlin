package com.kotlin.sample.persistence.address

import android.arch.persistence.room.*
import com.example.android.observability.persistence.User
import java.util.*

/**
 * Created by anna on 2017/12/13.
 */
@Entity(tableName = "address",
        indices = arrayOf(Index("city"), Index(value = *arrayOf("street", "city"), unique = true)),
        foreignKeys = arrayOf(ForeignKey(entity = User::class, parentColumns = arrayOf("id"), childColumns = arrayOf("user_id"))))
data class Address(@PrimaryKey
                   @ColumnInfo(name = "address_id")
                   val id: String = UUID.randomUUID().toString(),
                   val street: String,
                   val state: String,
                   val city: String,
                   @ColumnInfo(name = "post_code")
                   val postCode: String){

    @ColumnInfo(name = "user_id")
    lateinit var userId: String
}