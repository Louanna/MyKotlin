package com.kotlin.sample.persistence.user

import android.arch.persistence.room.ColumnInfo

/**
 * Created by anna on 17/01/2018.
 */
class NameTuple {
    @ColumnInfo(name = "first_name")
    var firstName: String? = ""

    @ColumnInfo(name = "last_name")
    var lastName: String? = ""
}