package com.kotlin.sample.ui

import android.content.Context
import com.example.android.observability.persistence.MyDatabase
import com.example.android.observability.ui.VMFactory
import com.kotlin.sample.persistence.IUserDataSource
import com.kotlin.sample.persistence.UserDataSource
import com.kotlin.sample.persistence.address.AddressDataSource
import com.kotlin.sample.persistence.address.IAddressDataSource
import com.kotlin.sample.persistence.book.BookDataSource
import com.kotlin.sample.persistence.book.IBookDataSource

/**
 * Created by anna on 2017/12/13.
 */
object Injection {

    private fun provideUserDataSource(context: Context): IUserDataSource {
        val database = MyDatabase.getInstance(context)
        return UserDataSource(database.userDao())
    }

    private fun provideAddressDataSource(context: Context): IAddressDataSource {
        val database = MyDatabase.getInstance(context)
        return AddressDataSource(database.addressDao())
    }

    private fun provideBookDataSource(context: Context): IBookDataSource {
        val database = MyDatabase.getInstance(context)
        return BookDataSource(database.bookDao())
    }

    fun provideViewModelFactory(context: Context): VMFactory {
        return VMFactory(provideUserDataSource(context), provideAddressDataSource(context),
                provideBookDataSource(context))
    }
}