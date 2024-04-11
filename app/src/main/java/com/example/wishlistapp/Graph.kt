package com.example.wishlistapp

import android.content.Context
import androidx.room.Room
import com.example.wishlistapp.data.WishDataBase
import com.example.wishlistapp.data.WishRepository

object Graph {
    lateinit var database: WishDataBase

    //by lazy, make sure that the variable is initialized when its needed
    val wishRepository by lazy {
        WishRepository(wishDao = database.wishDao())
    }


    fun provide(context: Context){
        database = Room.databaseBuilder(context,WishDataBase::class.java,"wishlist.db").build()
    }

}