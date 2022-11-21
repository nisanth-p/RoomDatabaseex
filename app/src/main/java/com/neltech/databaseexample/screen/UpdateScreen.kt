package com.neltech.databaseexample.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

//5
@Composable
fun UpdateScreen(
    viewModel: BookViewModel = hiltViewModel(),
    bookId: Int,
    navigateBack: () -> Unit
) {
    Scaffold(
        topBar = {},
        bottomBar = {},
        content = { padding ->
            LazyColumn(modifier = Modifier.padding(padding), content = {})

        }
    )
}