package com.codingwithjks.mvvmwithromandcoroutines.ViewModel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.codingwithjks.mvvmwithromandcoroutines.Model.User
import com.codingwithjks.mvvmwithromandcoroutines.Repository.UserRepository

class UserViewModel :ViewModel() {

    fun insert(context: Context,user:User)
    {
        UserRepository.insert(context,user)
    }

    fun getAllUserData(context: Context):LiveData<List<User>>
    {
        return UserRepository.getAllUserData(context)
    }
}