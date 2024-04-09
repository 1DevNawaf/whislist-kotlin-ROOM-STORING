package com.example.wishlistapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun AddEditDetailView(
    id:Long,
    viewModel: WishViewModel,
    navController: NavController
    ) {
    Scaffold(
        topBar = {
            AppBarView(
                title = if (id!=0L) "UpdateWish" else "Add Wish"
            ) {}
        }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(it)
                .wrapContentSize()
        ) {
            Spacer(modifier = Modifier.height(10.dp))


            WishTextField(
                    label = "Title", value = viewModel.wishTitleState, onValueChanged = {title->
                    viewModel.onWishTitleChanged(title)
                }
            )

            Spacer(modifier = Modifier.height(10.dp))


            WishTextField(
                label = "Description", value = viewModel.wishDescriptionState, onValueChanged = {description->
                    viewModel.onWishDescriptionChanged(description)
                }
            )

            Spacer(modifier = Modifier.height(10.dp))

            Button(
                onClick = {
                    if (viewModel.wishTitleState.isNotEmpty()
                        &&viewModel.wishDescriptionState.isNotEmpty())
                    {
                        //TODO update wish
                    }else{
                        //TODO Add wish
                    }

                }
            ) {
                Text(
                    text =  if (id !=0L) "Update Wish" else "Add wish",
                    style = TextStyle(fontSize = 18.sp)
                )
            }
        }

    }
}

