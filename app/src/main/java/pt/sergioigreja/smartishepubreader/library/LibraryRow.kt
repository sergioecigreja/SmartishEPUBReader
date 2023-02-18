package pt.sergioigreja.smartishepubreader.library

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import pt.sergioigreja.smartishepubreader.R
import pt.sergioigreja.smartishepubreader.models.Book

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LibraryRow(book: Book) {
        ListItem(
            headlineText = { Text(book.title) },
            supportingText = { Text(book.author) },
            leadingContent = {
                Image(
                    painter = painterResource(id = R.drawable.ic_baseline_book_48),
                    contentDescription = "Book cover"
                )
            }
        )
        Divider()

}

@Preview
@Composable
fun LibraryRowPreview() {
    Column {
        LibraryRow(book = Book("Harry Potter", "JK Rowling"))
    }
}