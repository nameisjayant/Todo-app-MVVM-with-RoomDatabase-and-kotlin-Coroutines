package com.codingwithjks.mvvmwithromandcoroutines.Repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.codingwithjks.mvvmwithromandcoroutines.Database.UserDatabase
import com.codingwithjks.mvvmwithromandcoroutines.Model.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class UserRepository {

    companion object{
        var userDatabase:UserDatabase?=null

        private fun intialiseDB(context:Context): UserDatabase?
        {
            return UserDatabase.getInstance(context)!!
        }

        fun insert(context: Context,user:User)
        {
            userDatabase= intialiseDB(context)

            CoroutineScope(IO).launch {
                userDatabase!!.userDao().insert(user)
            }
        }

        fun getAllUserData(context: Context): LiveData<List<User>>
        {
            userDatabase= intialiseDB(context)
            return userDatabase!!.userDao().getAllUserData()
        }
    }
}