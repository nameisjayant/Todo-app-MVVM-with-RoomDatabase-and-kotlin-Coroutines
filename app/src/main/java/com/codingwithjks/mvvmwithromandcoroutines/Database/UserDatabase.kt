package com.codingwithjks.mvvmwithromandcoroutines.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.codingwithjks.mvvmwithromandcoroutines.Dao.UserDao
import com.codingwithjks.mvvmwithromandcoroutines.Model.User

@Database(entities = [User::class],version = 1, exportSchema = false)
abstract class UserDatabase : RoomDatabase() {

    abstract fun userDao():UserDao


    companion object{
        @Volatile
         var instance:UserDatabase?=null
        private const val DATABASE_NAME="User"

        fun getInstance(context: Context):UserDatabase?
        {
            if(instance == null)
            {
                synchronized(UserDatabase::class.java)
                {
                    if(instance == null)
                    {
                        instance= Room.databaseBuilder(context,UserDatabase::class.java,
                            DATABASE_NAME)
                            .fallbackToDestructiveMigration()
                            .build()
                    }
                }
            }

            return instance
        }

    }
}