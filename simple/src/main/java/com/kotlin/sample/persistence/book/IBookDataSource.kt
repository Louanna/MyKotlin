package com.kotlin.sample.persistence.book

import io.reactivex.Flowable

/**
 * Created by anna on 17/01/2018.
 */
interface IBookDataSource {

    fun insertOrUpdateBook(book: Book)

    fun getAllBook(): Flowable<List<Book>>

    fun insertBookInTransaction()
}