package com.sli.happybirthdayapp.model

import android.content.Context
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

open class DataStoreHandlerImpl(
    private val scope: CoroutineScope
) : DataStoreHandler {

    private val dataStoreModel = DataStoreModel()

    override fun loadSavedName(context: Context) : Flow<String?> {
        return dataStoreModel.getDetails(DataStoreModel.NAME_KEY, context)
    }

    override fun loadSavedDoB(context: Context): Flow<Long?> {
        return dataStoreModel.getDetails(DataStoreModel.DOB_KEY, context)
    }

    override fun saveName(context: Context, name: String) {
        scope.launch(Dispatchers.IO) {
            dataStoreModel.saveDetails(DataStoreModel.NAME_KEY, name, context)
        }
    }

    override fun saveDateOfBirth(context: Context, dateOfBirth: Long) {
        scope.launch(Dispatchers.IO) {
            dataStoreModel.saveDetails(DataStoreModel.DOB_KEY, dateOfBirth, context)
        }
    }

}