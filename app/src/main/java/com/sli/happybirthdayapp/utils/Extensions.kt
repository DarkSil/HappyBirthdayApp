package com.sli.happybirthdayapp.utils

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.datastore.preferences.preferencesDataStore
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale

val Context.detailsDataStore by preferencesDataStore(name = "birthdayDetails")

fun Long.toDateString(): String {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        this.toDateStringApi26()
    } else {
        this.toDateStringLegacy()
    }
}

fun Long.toDateStringLegacy(): String {
    val sdf = java.text.SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    return sdf.format(Date(this))
}

@RequiresApi(Build.VERSION_CODES.O)
fun Long.toDateStringApi26() : String {
    val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    val date = Instant.ofEpochMilli(this)
        .atZone(ZoneId.systemDefault())
        .toLocalDate()
    return formatter.format(date)
}