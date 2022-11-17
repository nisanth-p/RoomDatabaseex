package com.neltech.databaseexample

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.neltech.databaseexample.screen.BookScreen
import com.neltech.databaseexample.screen.Screens
import com.neltech.databaseexample.screen.UpdateScreen

//3
@Composable
fun MyNavGraph(navControler: NavHostController) {
    NavHost(navController = navControler, startDestination = Screens.Book.route) {
        composable(route = Screens.Book.route) {
            BookScreen() { id ->
                navControler.navigate("${Screens.Update.route}/${id}")

            }
        }
        composable(route = Screens.Update.route) {
            UpdateScreen()
        }
    }
}