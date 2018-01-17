package com.kotlin.sample.persistence.book

import io.reactivex.Flowable

/**
 * Created by anna on 17/01/2018.
 */
class BookDataSource : IBookDataSource {

    private var mIBookDao: IBookDao

    constructor(bookDao: IBookDao) {
        this.mIBookDao = bookDao
    }

    override fun insertOrUpdateBook(book: Book) {
        return mIBookDao.insertBook(book)
    }

    override fun getAllBook(): Flowable<List<Book>> {
        return mIBookDao.getAllBook()
    }

    override fun insertBookInTransaction() {
        return mIBookDao.insertBookInTransaction()
    }

}