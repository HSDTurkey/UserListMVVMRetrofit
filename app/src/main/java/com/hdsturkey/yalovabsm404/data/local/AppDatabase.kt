package com.hdsturkey.yalovabsm404.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.hdsturkey.yalovabsm404.data.local.type_converters.NameTypeConverter
import com.hdsturkey.yalovabsm404.data.local.type_converters.UserPictureTypeConverter
import com.hdsturkey.yalovabsm404.fragments.user_list.model.User


// The class must be annotated with a @Database annotation that includes an entities array that
// lists all of the data entities associated with the database.

// The class must be an abstract class that extends RoomDatabase.

// For each DAO class that is associated with the database,
// the database class must define an abstract method that has zero arguments and returns an instance of the DAO class.


@Database(entities = [User::class], version = 1)
@TypeConverters(
    NameTypeConverter::class,
    UserPictureTypeConverter::class
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        private lateinit var instance: AppDatabase

        fun init(applicationContext: Context) {
            instance = Room.databaseBuilder(
                applicationContext,
                AppDatabase::class.java, "my-database-name"
            )
                .allowMainThreadQueries() //todo DONT USE THAT. USE DB WITH COROUTINES INSTEAD OF THAT
                .build()

        }

        fun getInstance(): AppDatabase {
            if (this::instance.isInitialized) {
                return instance
            } else {
                throw UninitializedPropertyAccessException("Please initialize the database before accessing instance")
            }
        }
    }

}