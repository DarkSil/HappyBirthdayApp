package com.sli.happybirthdayapp.model

import android.content.Context
import android.net.Uri
import androidx.compose.ui.graphics.ImageBitmap
import kotlinx.coroutines.flow.StateFlow

interface ImageCachingHandler {

    val savedBitmapState: StateFlow<ImageBitmap?>

    fun saveUri(context: Context, uri: Uri)

    fun loadFromCache(context: Context)

}