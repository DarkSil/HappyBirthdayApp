package com.sli.happybirthdayapp.presentation

import android.content.Context
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sli.happybirthdayapp.model.DataStoreModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.io.InputStream

class IntroViewModel : ViewModel() {

    private val dataStoreModel = DataStoreModel()

    private val savedBitmap = MutableStateFlow<ImageBitmap?>(null)
    val savedBitmapState = savedBitmap as StateFlow<ImageBitmap?>

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

    fun loadSavedName(context: Context) : Flow<String?> {
        return dataStoreModel.getDetails(DataStoreModel.NAME_KEY, context)
    }

    fun loadSavedDoB(context: Context): Flow<Long?> {
        return dataStoreModel.getDetails(DataStoreModel.DOB_KEY, context)
    }

    fun saveName(context: Context, name: String) {
        viewModelScope.launch {
            dataStoreModel.saveDetails(DataStoreModel.NAME_KEY, name, context)
        }
    }

    fun saveDateOfBirth(context: Context, dateOfBirth: Long) {
        viewModelScope.launch {
            dataStoreModel.saveDetails(DataStoreModel.DOB_KEY, dateOfBirth, context)
        }
    }
}