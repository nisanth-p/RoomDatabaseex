package com.neltech.databaseexample

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.neltech.databaseexample.screen.BookScreen
import com.neltech.databaseexample.screen.Constants.Companion.BOOK_ID
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
                    navHostControler.navigate("${Screens.Update.route}/${bookId}")
                },
                navController = navHostControler
            )

        }
        composable( route = "${Screens.Update.route}/{$BOOK_ID}",
            arguments = listOf(
                navArgument(BOOK_ID) {
                    type = NavType.IntType
                }
            )) {
            val bookId = it.arguments?.getInt(BOOK_ID) ?: 0
            Log.d(TAG, "MyNavGraph: "+bookId)
            UpdateScreen(bookId =bookId,
                navigateBack = {
                navHostControler.popBackStack()
            })
        }
    }
}