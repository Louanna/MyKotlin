package com.example.android.observability.persistence

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import android.content.Context
import com.kotlin.sample.persistence.address.Address
import com.kotlin.sample.persistence.Converters
import com.kotlin.sample.persistence.address.IAddressDao
import com.kotlin.sample.persistence.book.Book
import com.kotlin.sample.persistence.book.IBookDao

@Database(entities = arrayOf(User::class, Address::class, Book::class), version = 1)
@TypeConverters(Converters::class)
abstract class MyDatabase : RoomDatabase() {

    abstract fun userDao(): IUserDao
    abstract fun addressDao(): IAddressDao
    abstract fun bookDao(): IBookDao

    companion object {

        @Volatile private var INSTANCE: MyDatabase? = null

        fun getInstance(context: Context): MyDatabase =
                INSTANCE ?: synchronized(this) {
                    INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
                }

        private fun buildDatabase(context: Context) =
                Room.databaseBuilder(context.applicationContext,
                        MyDatabase::class.java, "Sample.db")
                        .fallbackToDestructiveMigration()
                        .build()
    }

//    Room.databaseBuilder(getApplicationContext(), MyDb.class, "database-name")
//    .addMigrations(MIGRATION_1_2, MIGRATION_2_3).build();
//
//    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
//        @Override
//        public void migrate(SupportSQLiteDatabase database) {
//            database.execSQL("CREATE TABLE `Fruit` (`id` INTEGER, "
//                    + "`name` TEXT, PRIMARY KEY(`id`))");
//        }
//    };
//
//    static final Migration MIGRATION_2_3 = new Migration(2, 3) {
//        @Override
//        public void migrate(SupportSQLiteDatabase database) {
//            database.execSQL("ALTER TABLE Book "
//                    + " ADD COLUMN pub_year INTEGER");
//        }
//    };

}
