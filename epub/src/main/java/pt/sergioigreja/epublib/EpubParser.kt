package pt.sergioigreja.epublib

import android.content.ContentResolver
import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import androidx.annotation.RequiresApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.*


class EpubParser(val context: Context) {
    val TAG = "EPUB_PARSER"
    var contentResolver: ContentResolver = context.contentResolver

    @RequiresApi(Build.VERSION_CODES.Q)
    suspend fun load(uri: Uri): Boolean {
        withContext(Dispatchers.IO) {
            var inputStream: InputStream? = null
            try {
                inputStream = contentResolver.openInputStream(uri)

                val file = File(context.cacheDir, "temp.epub")
                file.deleteOnExit()
                file.outputStream().use { output ->
                    inputStream!!.copyTo(output)
                }
                inputStream?.close()

                val projection = arrayOf(MediaStore.MediaColumns.DISPLAY_NAME)
                val metaCursor: Cursor? = contentResolver.query(uri, projection, null, null, null)
                var dirName = "new-book"
                metaCursor?.use {
                    if (metaCursor.moveToFirst()) {
                        dirName = metaCursor.getString(0)
                    }
                }
                val dir = File(context.filesDir, dirName)
                ZipUtils.unzip(file, dir.path)

            } catch (e: FileNotFoundException) {
                Log.e(TAG, e.message.orEmpty())
            } finally {
                inputStream!!.close()
            }
        }
        return true
    }
}