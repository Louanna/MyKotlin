package com.kotlin.sample.persistence.book

import android.arch.persistence.room.*
import io.reactivex.Flowable

/**
 * Created by anna on 17/01/2018.
 */
@Dao
abstract class IBookDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertBook(book: Book)

    @Query("SELECT * FROM book")
    abstract fun getAllBook(): Flowable<List<Book>>

    //TODO cannot worked
    @Transaction
    @Query("SELECT * FROM book")
    fun insertBookInTransaction() {
        var book = Book("book6", "1")
        insertBook(book)

        var book7 = Book("book7", "2")
        insertBook(book7)
    }
}