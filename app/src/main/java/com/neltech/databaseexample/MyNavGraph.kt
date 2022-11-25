package com.neltech.databaseexample

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.neltech.databaseexample.screen.BookScreen
import com.neltech.databaseexample.screen.Screens
import com.neltech.databaseexample.screen.UpdateScreen

private const val TAG = "MyNavGraph"

//3
@Composable
fun MyNavGraph(navHostControler: NavHostController) {
    NavHost(navController = navHostControler, startDestination = Screens.Book.route) {
        composable(route = Screens.Book.route) {

            BookScreen(
                navigateToUpdateBookScreen = { bookId ->

                }, navController = navHostControler
            )

        }
        composable(route = Screens.Update.route) {

            UpdateScreen(  bookId =0,navigateBack = {
                navHostControler.popBackStack()
            })
        }
    }
}