package com.kotlin.sample.persistence

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Index
import android.arch.persistence.room.PrimaryKey
import java.util.*

/**
 * Created by anna on 2017/12/13.
 */
@Entity(tableName = "address",
        indices = arrayOf(Index("city"), Index(value = *arrayOf("street", "city"), unique = true)))
data class Address(@PrimaryKey
                   @ColumnInfo(name = "address_id")
                   val id: String = UUID.randomUUID().toString(),
                   val street: String,
                   val state: String,
                   val city: String,
                   @ColumnInfo(name = "post_code")
                   val postCode: String)