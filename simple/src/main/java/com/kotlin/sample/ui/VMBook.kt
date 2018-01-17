package com.kotlin.sample.ui

import android.arch.lifecycle.ViewModel
import android.arch.persistence.room.Transaction
import android.util.Log
import com.example.android.observability.persistence.User
import com.example.android.observability.ui.VMUser
import com.kotlin.sample.persistence.IUserDataSource
import com.kotlin.sample.persistence.address.Address
import com.kotlin.sample.persistence.book.Book
import com.kotlin.sample.persistence.book.IBookDataSource
import io.reactivex.Completable
import io.reactivex.Flowable

/**
 * Created by anna on 17/01/2018.
 */
class VMBook(private val bookDataSource: IBookDataSource,
             private val userDataSource: IUserDataSource) : ViewModel() {

    fun getAllBook(): Flowable<List<Book>> {
        return bookDataSource.getAllBook()
    }

    fun insertBook(): Completable {
        return Completable.fromAction {

//            insertBookAndLinkedToUser()

            bookDataSource.insertBookInTransaction()
        }
    }

    //TODO cannot worked
    @Transaction
    private fun insertBookAndLinkedToUser() {
        // Anything inside this method runs in a single transaction.

//        val user = User(VMUser.USER_ID, "userName666")
//        val address = Address(VMUser.ADDRESS_ID, "street1", "state1", "city1", "215000")
//        address.userId = VMUser.USER_ID
//        user.address = address
//        userDataSource.insertOrUpdateUser(user)

        var book = Book("book1", "1")
        bookDataSource.insertOrUpdateBook(book)
        Log.d("debug", "bookId=" + book.bookId)

        bookDataSource.insertOrUpdateBook(Book("book2", "1"))

        val user = User(VMUser.USER_ID, "userName555")
        userDataSource.insertOrUpdateUser(user)

    }
}