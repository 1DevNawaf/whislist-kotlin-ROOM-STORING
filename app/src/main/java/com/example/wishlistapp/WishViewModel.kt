package com.example.wishlistapp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wishlistapp.data.Wish
import com.example.wishlistapp.data.WishRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class WishViewModel(
    //now we can use the repo within the entire app
    private val wishRepository: WishRepository = Graph.wishRepository
):ViewModel() {
    var wishTitleState by mutableStateOf("")
    var wishDescriptionState by mutableStateOf("")

    fun onWishTitleChanged(newString : String){
        wishTitleState = newString
    }

    fun onWishDescriptionChanged(newString : String){
        wishDescriptionState=newString
    }

    lateinit var getAllWises: Flow<List<Wish>>

    init {
        viewModelScope.launch {
            getAllWises = wishRepository.getWishes()
        }
    }

    fun addWish(wish: Wish){
        /*
        Dispatchers :
        by using it you optimize the coroutine and make it very efficient
        this allows for efficient management of threads when you use many coroutines for
        IO tasks
         */
        viewModelScope.launch(Dispatchers.IO) {
            wishRepository.addWish(wish= wish)
        }
    }

    fun getWishById(id:Long):Flow<Wish>{
        return wishRepository.getWishById(id)
    }


    fun updateWish(wish: Wish){
        viewModelScope.launch(Dispatchers.IO) {
            wishRepository.updateWish(wish= wish)
        }
    }

    fun deleteWish(wish: Wish){
        viewModelScope.launch(Dispatchers.IO) {
            wishRepository.deleteWish(wish= wish)
        }
    }


}