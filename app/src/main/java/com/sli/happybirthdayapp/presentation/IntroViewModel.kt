package com.sli.happybirthdayapp.presentation

import android.content.Context
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.io.InputStream

class IntroViewModel : ViewModel() {

    val savedBitmap = MutableStateFlow<ImageBitmap?>(null)

    // I thought to make the process of saving parallel to the process that updates the Compose
    // but I decided to block the process for a small time to make user *wait* for an update
    // in case of huge image loads. I know this process does not block the UI thread (that's the point)
    // I also decided not to add Circular Progress Bar since in most cases it won't even show up and
    // I don't really want to spend time on this (I have other part to do)

    fun saveUriToCache(context: Context, uri: Uri) {
        viewModelScope.launch(Dispatchers.IO) {

            // TODO Save uri to cache as file

            var stream : InputStream? = null
            try {
                stream = context.contentResolver.openInputStream(uri)
                savedBitmap.value = BitmapFactory.decodeStream(stream).asImageBitmap()
            } catch (e: Exception) {
                println(e.localizedMessage)
            } finally {
                stream?.close()
            }
        }
    }

    fun tryToGetBitmapFromCache() {
        if (savedBitmap.value == null) {
            // TODO Check if file exists, if so - decode to Bitmap
        }
    }
}