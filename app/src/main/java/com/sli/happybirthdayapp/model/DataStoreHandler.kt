package com.sli.happybirthdayapp.model

import android.content.Context
import kotlinx.coroutines.flow.Flow

interface DataStoreHandler {

    fun loadSavedName(context: Context) : Flow<String?>

    fun loadSavedDoB(context: Context): Flow<Long?>

    fun saveName(context: Context, name: String)

    fun saveDateOfBirth(context: Context, dateOfBirth: Long)

}