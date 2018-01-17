package com.kotlin.sample.persistence.book

import android.arch.persistence.room.*
import com.example.android.observability.persistence.User

/**
 * Created by anna on 2017/12/13.
 */
@Entity(tableName = "book", foreignKeys = arrayOf(ForeignKey(entity = User::class,
        parentColumns = arrayOf("id"), childColumns = arrayOf("user_id"))),
        indices = arrayOf(Index(value = *arrayOf("user_id", "book_id"))))
data class Book(
        var title: String,
        @ColumnInfo(name = "user_id")
        var userId: String = "") {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "book_id")
    var bookId: Long = 0

    override fun toString(): String {
        return "bookId=  $bookId title=  $title"
    }

}