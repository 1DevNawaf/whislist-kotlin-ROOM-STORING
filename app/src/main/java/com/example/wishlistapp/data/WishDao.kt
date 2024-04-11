package com.example.wishlistapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

/*

Defining The DAOs :

    The entity class will be used to create the DAO class providing the
    methods need for the CRUD operations.

    so within the app we will perform :
        1- @Insert
        2- @Query
        3- @Update
        4- @Delete

    note that the insert, update and delete are suspend functions
 */



//Dao must be an abstract class that have abstract fun, because the class it self don't need to perform anything
@Dao
abstract class WishDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)//when conflict accrue, just ignore
    abstract suspend fun addWish(wishEntity: Wish)//best practice to call the object an object's entity

    //define what needs to be fetched
    @Query("SELECT * FROM `wish-table`")
    //Flow is a reactive stream, like the suspend, its a part of the Kotlin coroutines
    //because of that it doesn't need to be suspend hence its already a flow
    abstract fun getAllWishes(): Flow<List<Wish>>


    //edit an entry
    @Update
    abstract suspend fun updateWish(wishEntity: Wish)

    //delete an entry
    @Delete
    abstract suspend fun deleteWish(wishEntity: Wish)

    //fetch a wish by its id
    @Query("SELECT * FROM `wish-table` WHERE id=:id")
    abstract fun getWishById(id:Long): Flow<Wish>
}