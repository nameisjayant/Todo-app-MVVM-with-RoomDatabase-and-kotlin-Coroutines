package com.codingwithjks.mvvmwithromandcoroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codingwithjks.mvvmwithromandcoroutines.Adapter.UserAdapter
import com.codingwithjks.mvvmwithromandcoroutines.Model.User

import com.codingwithjks.mvvmwithromandcoroutines.ViewModel.UserViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var userViewModel: UserViewModel
    private lateinit var builder:AlertDialog.Builder
    private lateinit var dialog: AlertDialog
    private lateinit var name:EditText
    private lateinit var age:EditText
    private lateinit var save:Button
    private lateinit var recyclerView: RecyclerView
    private lateinit var userAdapter: UserAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView=findViewById(R.id.recyclerView)
        userAdapter= UserAdapter(this, ArrayList<User>())
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager=LinearLayoutManager(this@MainActivity)
            adapter=userAdapter
        }
        userViewModel=ViewModelProvider(this).get(UserViewModel::class.java)
        userViewModel.getAllUserData(this).observe(this, Observer {
           userAdapter.setData(it as ArrayList<User>)
        })
        floatingActionButton.setOnClickListener {
            openDialog()
        }
    }

    private fun openDialog() {
        builder=AlertDialog.Builder(this)
        var itemView:View=LayoutInflater.from(applicationContext).inflate(R.layout.dialog,null)
        dialog=builder.create()
        dialog.setView(itemView)
        name=itemView.findViewById(R.id.name1)
        age=itemView.findViewById(R.id.age1)
        save=itemView.findViewById(R.id.save)
        save.setOnClickListener {
            saveDataIntoDatabase()
        }
       dialog.show()
    }

    private fun saveDataIntoDatabase() {
        val getName=name.text.toString().trim()
        val getAge=age.text.toString().trim()
        if(!TextUtils.isEmpty(getName) && !TextUtils.isEmpty(getAge))
        {
            userViewModel.insert(this,User(getName,Integer.parseInt(getAge)))
            Toast.makeText(applicationContext,"Data added successfully..",Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }
        else
        {
            Toast.makeText(applicationContext,"Please fill all the fields..",Toast.LENGTH_SHORT).show()
        }
    }
}