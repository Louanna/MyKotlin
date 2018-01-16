package com.kotlin.sample.persistence

import android.arch.persistence.room.TypeConverter
import java.util.*

/**
 * Created by anna on 2017/12/13.
 */
class Converters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return if (value == null) null else Date(value)
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }
}