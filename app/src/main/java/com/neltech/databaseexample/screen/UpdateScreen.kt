package com.neltech.databaseexample.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.Composable
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
                    viewModel.updateTitle(it)
                },
                updatePrrice = {
                    if (it.isNotEmpty())
                       viewModel.updateAuthor(it)
                },
                updateBook = {
                    if (it!=null)
                    viewModel.updateBook(it)
                },
                navigateBack = {
                    navigateBack()
                }
            )

        }
    )
}

@Composable
fun UpdateBookContent(
    padding: PaddingValues,
    book: BookModel,
    updateTitle: (title: String) -> Unit,
    updatePrrice: (price: String) -> Unit,
    updateBook: (book: BookModel) -> Unit,
    navigateBack: () -> Unit
) {

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
                updatePrrice(price)
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