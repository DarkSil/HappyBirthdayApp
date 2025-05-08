package com.sli.happybirthdayapp.model

import android.content.Context
import android.net.Uri
import androidx.compose.ui.graphics.ImageBitmap
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

open class ImageCachingHandlerImpl(private val scope: CoroutineScope) : ImageCachingHandler {

    private val imageCachingModel = ImageCachingModel()

    private val savedBitmap = MutableStateFlow<ImageBitmap?>(null)
    override val savedBitmapState = savedBitmap as StateFlow<ImageBitmap?>

    // I thought to make the process of saving parallel to the process that updates the Compose
    // but I decided to block the process for a small time to make user *wait* for an update
    // in case of huge image loads. I know this process does not block the UI thread (that's the point)
    // I also decided not to add Circular Progress Bar since in most cases it won't even show up

    override fun saveUri(context: Context, uri: Uri) {
        scope.launch(Dispatchers.IO) {
            imageCachingModel.saveUriAsFile(context, uri)
            savedBitmap.value = imageCachingModel.loadImageBitmap(context)
        }
    }

    override fun loadFromCache(context: Context) {
        if (savedBitmap.value == null) {
            scope.launch(Dispatchers.IO) {
                savedBitmap.value = imageCachingModel.loadImageBitmap(context)
            }
        }
    }

}