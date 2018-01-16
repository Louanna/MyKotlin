package com.example.android.observability.persistence

import android.arch.persistence.room.*
import android.graphics.Bitmap
import com.kotlin.sample.persistence.Address
import java.util.*

/**
 * Created by anna on 2017/12/13.
 */

@Entity(tableName = "users")
data class User(@PrimaryKey
                @ColumnInfo(name = "id")
                val id: String = UUID.randomUUID().toString(),
                @ColumnInfo(name = "name")
                val userName: String) {

    public lateinit var createTime: Date
    @Embedded
    lateinit var address: Address
    @Ignore
    lateinit internal var picture: Bitmap

}
