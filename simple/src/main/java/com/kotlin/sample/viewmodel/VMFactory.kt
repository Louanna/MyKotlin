package com.example.android.observability.ui

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.kotlin.sample.persistence.IUserDataSource
import com.kotlin.sample.persistence.address.IAddressDataSource
import com.kotlin.sample.persistence.book.IBookDataSource
import com.kotlin.sample.viewmodel.VMAddress
import com.kotlin.sample.viewmodel.VMApp
import com.kotlin.sample.viewmodel.VMBook

/**
 * Created by anna on 2017/12/13.
 */
class VMFactory(private val userDataSource: IUserDataSource,
                private val addressDataSource: IAddressDataSource,
                private val bookDataSource: IBookDataSource) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(VMUser::class.java)) {
            return VMUser(userDataSource) as T
        } else if (modelClass.isAssignableFrom(VMAddress::class.java)) {
            return VMAddress(addressDataSource) as T
        } else if (modelClass.isAssignableFrom(VMBook::class.java)) {
            return VMBook(bookDataSource, userDataSource) as T
        } else if (modelClass.isAssignableFrom(VMApp::class.java)) {
            return VMApp() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}
