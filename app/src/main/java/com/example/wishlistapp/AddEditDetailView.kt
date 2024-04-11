package com.example.wishlistapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Button

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.wishlistapp.data.Wish
import kotlinx.coroutines.launch

@Composable
fun AddEditDetailView(
    id:Long,
    viewModel: WishViewModel,
    navController: NavController
    ) {

    val snackMessage = remember {
        mutableStateOf("")
    }

    //we can use it to run coroutine methods
    val scope = rememberCoroutineScope()

    //its taking care of everything inside the scaffold
    val scaffoldState = rememberScaffoldState()

    if (id != 0L){
        val wish = viewModel.getWishById(id).collectAsState(initial = Wish(0L,"",""))
        viewModel.wishTitleState = wish.value.title
        viewModel.wishDescriptionState = wish.value.description
    }else{
        viewModel.wishTitleState = ""
        viewModel.wishDescriptionState = ""
    }





    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            AppBarView(title = if (id!=0L) "Update Wish" else "Add Wish")
            {navController.navigateUp()}
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
                        if (id != 0L){
                            viewModel.updateWish(
                                Wish(
                                    id=id,
                                    title = viewModel.wishTitleState.trim(),
                                    description = viewModel.wishDescriptionState.trim()
                                )
                            )
                        }else{
                            //Add wish
                            viewModel.addWish(
                                Wish(title = viewModel.wishTitleState.trim(),
                                    description = viewModel.wishDescriptionState.trim())
                            )
                            snackMessage.value="wish has been created"
                        }

                    }else{
                        snackMessage.value="Enter fields to create a wish"
                    }
                    scope.launch {
                        //scaffoldState.snackbarHostState.showSnackbar(snackMessage.value)
                        navController.navigateUp()
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

