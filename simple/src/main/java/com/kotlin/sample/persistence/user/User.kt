package com.example.android.observability.persistence

import android.arch.persistence.room.*
import com.kotlin.sample.persistence.address.Address
import java.util.*

/**
 * Created by anna on 2017/12/13.
 */

// 联合主键
// @Entity(primaryKeys = {"firstName", "lastName"})

@Entity(tableName = "users")
data class User(@PrimaryKey
                @ColumnInfo(name = "id")
                val id: String = UUID.randomUUID().toString(),
                @ColumnInfo(name = "name")
                val userName: String) {

    var createTime: Date? = Date(System.currentTimeMillis())

    @Embedded
//    @Embedded(prefix = "address_")
    lateinit var address: Address

    @ColumnInfo(name = "first_name")
    var firstName: String? = ""
    @ColumnInfo(name = "last_name")
    var lastName: String? = ""

//    @Ignore
//    lateinit internal var picture: Bitmap

    override fun toString(): String {
        return super.toString() + " address= " + address.toString()
    }

}
