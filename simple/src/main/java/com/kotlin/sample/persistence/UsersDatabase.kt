/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.observability.persistence

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import android.content.Context
import com.kotlin.sample.persistence.Converters

/**
 * The Room database that contains the Users table
 */
@Database(entities = arrayOf(User::class), version = 1)
@TypeConverters(Converters::class)
abstract class UsersDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {

        @Volatile private var INSTANCE: UsersDatabase? = null

        fun getInstance(context: Context): UsersDatabase =
                INSTANCE ?: synchronized(this) {
                    INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
                }

        private fun buildDatabase(context: Context) =
                Room.databaseBuilder(context.applicationContext,
                        UsersDatabase::class.java, "Sample.db")
                        .build()
    }
}
