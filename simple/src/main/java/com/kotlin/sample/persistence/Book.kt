package com.kotlin.sample.persistence

import android.arch.persistence.room.*
import com.example.android.observability.persistence.User

/**
 * Created by anna on 2017/12/13.
 */
@Entity(tableName = "book", foreignKeys = arrayOf(ForeignKey(entity = User::class,
        parentColumns = arrayOf("id"), childColumns = arrayOf("user_id"))),
        indices = arrayOf(Index(value = *arrayOf("user_id", "book_id"))))
data class Book(@PrimaryKey
                @ColumnInfo(name = "book_id")
                var bookId: Int = 0,
                var title: String,
                @ColumnInfo(name = "user_id")
                var userId: Int = 0
)