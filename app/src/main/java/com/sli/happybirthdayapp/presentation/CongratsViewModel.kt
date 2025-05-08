package com.sli.happybirthdayapp.presentation

import android.content.Context
import android.net.Uri
import androidx.compose.ui.graphics.ImageBitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sli.happybirthdayapp.model.DataStoreHandler
import com.sli.happybirthdayapp.model.DataStoreHandlerImpl
import com.sli.happybirthdayapp.model.ImageCachingHandler
import com.sli.happybirthdayapp.model.ImageCachingHandlerImpl
import com.sli.happybirthdayapp.model.TimeFrame
import com.sli.happybirthdayapp.utils.getYearsAndMonthsFromMillis
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

class CongratsViewModel : ViewModel(), ImageCachingHandler, DataStoreHandler {

    private val imageCachingHandler = ImageCachingHandlerImpl(viewModelScope)
    private val dataStoreHandler = DataStoreHandlerImpl(viewModelScope)

    override val savedBitmapState: StateFlow<ImageBitmap?>
        get() = imageCachingHandler.savedBitmapState

    override fun saveUri(context: Context, uri: Uri) {
        imageCachingHandler.saveUri(context, uri)
    }

    override fun loadFromCache(context: Context) {
        imageCachingHandler.loadFromCache(context)
    }

    override fun loadSavedName(context: Context): Flow<String?> {
        return dataStoreHandler.loadSavedName(context)
    }

    override fun loadSavedDoB(context: Context): Flow<Long?> {
        return dataStoreHandler.loadSavedDoB(context)
    }

    override fun saveName(context: Context, name: String) {
        dataStoreHandler.saveName(context, name)
    }

    override fun saveDateOfBirth(context: Context, dateOfBirth: Long) {
        dataStoreHandler.saveDateOfBirth(context, dateOfBirth)
    }

    fun getTimeFrame(value: Long) : TimeFrame {
        val pair = value.getYearsAndMonthsFromMillis()
        val (years, months) = pair

        return if (years > 0) {
            TimeFrame.Year(years)
        } else {
            TimeFrame.Month(months)
        }
    }
}