package com.example.wishlistapp

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun Navigation(viewModel: WishViewModel = viewModel(),navController: NavHostController = rememberNavController()) {
    NavHost(navController = navController, startDestination = Screen.HomeScreen.rout) {
        composable(Screen.HomeScreen.rout){
            HomeView(navController,viewModel)
        }

        composable(Screen.AddScreen.rout){
            AddEditDetailView(id = 0L, viewModel = viewModel, navController = navController)
        }
    }
}