package com.sli.happybirthdayapp.model

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.sli.happybirthdayapp.utils.detailsDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataStoreModel {

    companion object {
        val NAME_KEY = stringPreferencesKey("NAME_KEY")
        val DOB_KEY = longPreferencesKey("DOB_KEY")
    }

    suspend fun <T> saveDetails(key: Preferences.Key<T>, value: T, context: Context) {
        context.detailsDataStore.edit { details ->
            details[key] = value
        }
    }

    fun <T> getDetails(key: Preferences.Key<T>, context: Context) : Flow<T?> {
        return context.detailsDataStore.data.map { it[key] }
    }

}