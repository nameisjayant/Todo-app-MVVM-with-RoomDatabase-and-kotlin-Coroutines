package com.codingwithjks.mvvmwithromandcoroutines.Dao

import android.provider.ContactsContract
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.codingwithjks.mvvmwithromandcoroutines.Model.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
   suspend fun insert(user:User)

    @Query("SELECT * FROM user ORDER BY id ASC")
    fun getAllUserData():LiveData<List<User>>
}