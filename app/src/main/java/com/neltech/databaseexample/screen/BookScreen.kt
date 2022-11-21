package com.neltech.databaseexample.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.neltech.databaseexample.model.BookModel
import com.neltech.databaseexample.repo.ListBooks
import kotlinx.coroutines.job

//4
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BookScreen(
    viewModel: BookViewModel = hiltViewModel(),
    navigateToUpdateBookScreen: (id: Int) -> Unit,
    navController: NavController
) {
    val books by viewModel.books.collectAsState(
        initial = mutableListOf<BookModel>(BookModel(1, "Android", 99), BookModel(1, "Kotlin", 199))
    )
    Scaffold(
        topBar = {},
        bottomBar = {},
        floatingActionButton = {
            AddBookFloatingActionButton(
                addBookButtonClick = {
                    viewModel.openDialog()
                }
            )
        },
        content = { padding ->
            BooksContent(padding, books,

                onDeleteBook = {
                    viewModel.deleteBook(it)
                },
                navigateToUpdateBookScreen = { id ->
                  //  navigateToUpdateBookScreen(id)
                    navController.navigate(route = Screens.Update.route)
                })
            AddBooksAlertBoxContent(padding,
                viewModel.openDialog,
                closeDialog = {
                    viewModel.closeDialog()
                }, addBook = {
                    viewModel.addBook(it)
                })
        }


    )
}

@Composable
fun AddBookFloatingActionButton(addBookButtonClick: () -> Unit) {
    FloatingActionButton(onClick = { addBookButtonClick() }) {
        Icon(imageVector = Icons.Default.Add, contentDescription = "books add button")
    }
}

@Composable
@ExperimentalMaterialApi
fun BooksContent(
    padding: PaddingValues, books: ListBooks,
    onDeleteBook: (book: BookModel) -> Unit,
    navigateToUpdateBookScreen: (id: Int) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(padding)
    ) {
        items(books) { book ->

            BookCard(book, onDelete = {
                onDeleteBook(book)
            }, navigateToUpdateBookScreen = { id ->
                navigateToUpdateBookScreen(id)
            }
            )


        }
    }
}

@Composable
@ExperimentalMaterialApi
fun BookCard(
    book: BookModel,
    onDelete: () -> Unit,
    navigateToUpdateBookScreen: (id: Int) -> Unit
) {
    Card(
        shape = MaterialTheme.shapes.small,
        modifier = Modifier
            .padding(
                start = 8.dp,
                end = 8.dp,
                top = 4.dp,
                bottom = 4.dp
            )
            .fillMaxWidth(),
        elevation = 3.dp,
        onClick = {
            navigateToUpdateBookScreen(book.id)
        }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column {
                Text(
                    text = book.name,
                    color = Color.DarkGray,
                    fontSize = 25.sp
                )
                Text(
                    text = "${book.price}$",
                    color = Color.DarkGray,
                    fontSize = 12.sp,
                    textDecoration = TextDecoration.Underline
                )
            }
            Spacer(
                modifier = Modifier.weight(1f)
            )
            IconButton(onClick = {
                onDelete()

            }) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = "delete")
            }
        }
    }
}

@Composable
@ExperimentalMaterialApi
fun AddBooksAlertBoxContent(
    padding: PaddingValues,
    openDialog: Boolean,
    closeDialog: () -> Unit,
    addBook: (book: BookModel) -> Unit
) {
    if (openDialog) {
        var title by remember { mutableStateOf("") }
        var rupees by remember { mutableStateOf("") }
        val focusRequester = FocusRequester()
        AlertDialog(
            onDismissRequest = { closeDialog() },
            confirmButton = {
                TextButton(onClick = {

                    closeDialog()
                    val book = BookModel(0, name = title, price = rupees.toInt())
                    addBook(book)

                }) {
                    Text(text = "Yes")
                }
            },
            dismissButton = {
                TextButton(onClick = { closeDialog() }) {
                    Text(text = "No")
                }
            },
            title = {
                Text(text = "Add Your Books")
            }, text = {

                Column {
                    TextField(
                        value = title,
                        onValueChange = {
                            title = it
                        },
                        placeholder = {
                            Text(
                                text = "BOOK TITLE"
                            )
                        },
                        modifier = Modifier.focusRequester(focusRequester)
                    )
                    LaunchedEffect(Unit) {
                        coroutineContext.job.invokeOnCompletion {
                            focusRequester.requestFocus()
                        }
                    }
                    Spacer(
                        modifier = Modifier.height(16.dp)
                    )
                    TextField(
                        value = rupees,
                        onValueChange = {
                            rupees = it
                        },
                        placeholder = {
                            Text(
                                text = "PRICE"
                            )
                        }
                    )
                }
            })
    }
}