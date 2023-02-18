package pt.sergioigreja.smartishepubreader.library

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import pt.sergioigreja.smartishepubreader.models.Book

class LibraryScreenViewModel: ViewModel() {
    var bookList = mutableStateOf(listOf(
        Book(title = "Harry Potter", author = "J.K. Rowling"),
        Book(title = "The White Tower", author = "John Doe")
    ))

}