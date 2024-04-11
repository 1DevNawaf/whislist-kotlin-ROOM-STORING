package com.example.wishlistapp.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "wish-table")//assigning the table and its name
data class Wish(
    //the primary key, also will start with id 0 and will be auto incremented
    @PrimaryKey(autoGenerate = true)
    val id: Long=0,

    //each row is an entity of the table, and each column is contains the data of an entity
    @ColumnInfo(name = "wish-title")
    val title:String,

    @ColumnInfo(name = "wish-desc")
    val description:String
)

object DummyWish{
    val wishList = listOf(
        Wish(title = "Samsung S24+", description = "Powerful Phone"),
        Wish(title = "Meta Quest 3", description = "VR"),
        Wish(title = "Samsung S24+", description = "Powerful Phone")
    )
}
