package com.example.wishlistapp.data

data class Wish(
    val id: Long=0,
    val title:String,
    val description:String
)

object DummyWish{
    val wishList = listOf(
        Wish(title = "Samsung S24+", description = "Powerful Phone"),
        Wish(title = "Meta Quest 3", description = "VR"),
        Wish(title = "Samsung S24+", description = "Powerful Phone")
    )
}
