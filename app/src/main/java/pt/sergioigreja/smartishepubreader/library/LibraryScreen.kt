package pt.sergioigreja.smartishepubreader

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.compose.SmartishEPUBReaderTheme
import pt.sergioigreja.smartishepubreader.library.LibraryRow
import pt.sergioigreja.smartishepubreader.library.LibraryScreenViewModel

@Composable
fun LibraryScreen(viewModel: LibraryScreenViewModel = viewModel()) {
    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(12.dp)
            .fillMaxSize()
    ) {
        Column(verticalArrangement = Arrangement.Top, horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Library", style = MaterialTheme.typography.h2, fontWeight = FontWeight.Bold)
            Column(verticalArrangement = Arrangement.Top, modifier = Modifier.verticalScroll(ScrollState(0))) {
                viewModel.bookList.value.map {
                    LibraryRow(book = it)
                }
            }
        }

        Button(onClick = { /*TODO*/ }, modifier = Modifier.weight(1f, false)) {
            Icon(Icons.Filled.Add, contentDescription = "")
            Text(text = "Import Book")
        }
    }
}

@Preview
@Composable
fun LibraryScreenViewModel() {
    SmartishEPUBReaderTheme {
        LibraryScreen()
    }
}