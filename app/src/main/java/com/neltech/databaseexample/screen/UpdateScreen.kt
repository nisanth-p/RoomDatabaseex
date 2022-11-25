package com.neltech.databaseexample.screen

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.neltech.databaseexample.model.BookModel

//5
@Composable
fun UpdateScreen(
    viewModel: BookViewModel = hiltViewModel(),
    bookId: Int,
    navigateBack: () -> Unit
) {
    LaunchedEffect(Unit) {
        viewModel.getBook(bookId)
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "UPDATE_BOOK_SCREEN"
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = navigateBack
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.ArrowBack,
                            contentDescription = null,
                        )
                    }
                }
            )
        },
        bottomBar = {},
        content = { padding ->
            UpdateBookContent(padding, viewModel.book,
                updateTitle = {

                    if (it.isNotEmpty())
                       viewModel.updateTitle(it)
                },
                updatePrice = {

                       viewModel.updateAuthor(it)
                },
                updateBook = {

                    viewModel.updateBook(it)
                },
                navigateBack = {
                    navigateBack()
                }
            )

        }
    )
}
private  val TAG = "UpdateScreen"
@Composable
fun UpdateBookContent(
    padding: PaddingValues,
    book: BookModel,
    updateTitle: (title: String) -> Unit,
    updatePrice: (price: String) -> Unit,
    updateBook: (book: BookModel) -> Unit,
    navigateBack: () -> Unit
) {
    Log.d(TAG, "UpdateBookContent: "+book)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TextField(
            value = book.name,
            onValueChange = { title ->
                updateTitle(title)
            },
            placeholder = {
                Text(
                    text = "Enter Book Title"
                )
            }
        )
        Spacer(
            modifier = Modifier.height(8.dp)
        )
        TextField(
            value = book.price.toString(),
            onValueChange = { price ->
                book.price =price.toInt()
                Log.d(TAG, "UpdateBookContent: "+price)
                updatePrice(price)
            },
            placeholder = {
                Text(
                    text = "Enter price"
                )
            }
        )
        Spacer(
            modifier = Modifier.height(8.dp)
        )


        Button(
            onClick = {
                updateBook(book)
                navigateBack()
            }
        ) {
            Text(
                text = "UPDATE"
            )
        }
    }


}