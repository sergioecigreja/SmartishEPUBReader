package pt.sergioigreja.smartishepubreader

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.compose.SmartishEPUBReaderTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val epubUri =  mutableStateOf("No file selected")

        val getContent = registerForActivityResult(ActivityResultContracts.GetContent())  { uri: Uri? ->
            epubUri.value = uri.toString()  // Handle the returned Uri
        }

        setContent {

            SmartishEPUBReaderTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    /*
                                        Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(text = epubUri.value)
                        Button({getContent.launch("application/epub+zip")}){
                            Text(text = "Open File")
                        }
                    }
                     */
                    LibraryScreen()

                }

            }
        }
    }

}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SmartishEPUBReaderTheme {
        Greeting("Android")
    }
}