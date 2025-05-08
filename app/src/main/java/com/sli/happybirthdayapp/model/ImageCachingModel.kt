package com.sli.happybirthdayapp.model

import android.content.Context
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import java.io.File

class ImageCachingModel {

    companion object {
        // Not specifying the extension
        const val FILE_NAME = "cached_image"
    }

    suspend fun saveUriAsFile(context: Context, uri: Uri) : File? {
        val stream = context.contentResolver.openInputStream(uri) ?: return null
        val file = File(context.cacheDir, FILE_NAME)

        stream.use { input ->
            file.outputStream().use { output ->
                input.copyTo(output)
            }
        }

        return file
    }

    suspend fun loadImageBitmap(context: Context) : ImageBitmap? {
        val file = File(context.cacheDir, FILE_NAME)
        return BitmapFactory.decodeFile(file.absolutePath)?.asImageBitmap()
    }

}