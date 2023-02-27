package pt.sergioigreja.smartishepubreader

import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.compose.SmartishEPUBReaderTheme
import kotlinx.coroutines.launch
import pt.sergioigreja.epublib.EpubParser
import pt.sergioigreja.smartishepubreader.library.LibraryRow
import pt.sergioigreja.smartishepubreader.library.LibraryScreenViewModel

@Composable
fun LibraryScreen(viewModel: LibraryScreenViewModel = viewModel()) {
    val scope = rememberCoroutineScope()
    val context = LocalContext.current


    val getContent =
        rememberLauncherForActivityResult(ActivityResultContracts.OpenDocument()) { uri: Uri? ->

            if (uri != null) {
                scope.launch {

                    val resolver = context.contentResolver
                    val takeFlags: Int = Intent.FLAG_GRANT_READ_URI_PERMISSION and
                            Intent.FLAG_GRANT_WRITE_URI_PERMISSION
                    resolver.takePersistableUriPermission(uri, takeFlags)

                    try {
                        val epubParser = EpubParser(context)
                        epubParser.load(uri)
                    }catch (e: Exception) {
                        Log.e("APP", e.message.orEmpty())
                    }

                }
            } else {
                println("oops")
            }
        }

    /*val requestPermissionLauncher =
        rememberLauncherForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                getContent.launch(arrayOf("application/epub+zip"))
            } else {
                // Explain to the user that the feature is unavailable because the
                // feature requires a permission that the user has denied. At the
                // same time, respect the user's decision. Don't link to system
                // settings in an effort to convince the user to change their
                // decision.
                println("oops")
            }
        }*/



    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(12.dp)
            .fillMaxSize()
    ) {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Library",
                style = MaterialTheme.typography.h2,
                fontWeight = FontWeight.Bold
            )
            Column(
                verticalArrangement = Arrangement.Top,
                modifier = Modifier.verticalScroll(ScrollState(0))
            ) {
                viewModel.bookList.value.map {
                    LibraryRow(book = it)
                }
            }
        }

        Button(
            onClick = {
                //requestPermissionLauncher.launch(android.Manifest.permission.READ_EXTERNAL_STORAGE)
                getContent.launch(arrayOf("application/epub+zip"))
                      },
            modifier = Modifier.weight(1f, false)
        ) {
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