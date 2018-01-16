package com.kotlin.sample

import android.content.Context
import com.example.android.observability.persistence.UsersDatabase
import com.example.android.observability.ui.ViewModelFactory
import com.kotlin.sample.persistence.LocalUserDataSource
import com.kotlin.sample.persistence.UserDataSource

/**
 * Created by anna on 2017/12/13.
 */
object Injection {

    fun provideUserDataSource(context: Context): UserDataSource {
        val database = UsersDatabase.getInstance(context)
        return LocalUserDataSource(database.userDao())
    }

    fun provideViewModelFactory(context: Context): ViewModelFactory {
        val dataSource = provideUserDataSource(context)
        return ViewModelFactory(dataSource)
    }
}