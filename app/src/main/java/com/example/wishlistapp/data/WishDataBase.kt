package com.example.wishlistapp.data

import androidx.room.Database
import androidx.room.RoomDatabase


// we use this Database to get access to the Dao and its methods
@Database(
    entities = [Wish::class],//the entity is from which class
    version = 1,//the version of your database, not the version of the ROOM
    exportSchema = false
)
abstract class WishDataBase : RoomDatabase(){
    abstract fun wishDao(): WishDao
}