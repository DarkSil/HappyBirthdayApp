package com.sli.happybirthdayapp.utils

import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.content.FileProvider
import androidx.datastore.preferences.preferencesDataStore
import java.io.File
import java.io.FileOutputStream
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Date
import java.util.Locale

val Context.detailsDataStore by preferencesDataStore(name = "birthdayDetails")

val Float.px: Float
    get() = this * Resources.getSystem().displayMetrics.density

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

fun Long.getYearsAndMonthsFromMillis(): Pair<Int, Int> {
    val past = Calendar.getInstance().apply { timeInMillis = this@getYearsAndMonthsFromMillis }
    val now = Calendar.getInstance()

    var years = now.get(Calendar.YEAR) - past.get(Calendar.YEAR)
    var months = now.get(Calendar.MONTH) - past.get(Calendar.MONTH)

    if (months < 0) {
        years -= 1
        months += 12
    }

    return Pair(years, months)
}

fun Bitmap.saveBitmapToCache(context: Context): Uri {
    val cachePath = File(context.cacheDir, "images")
    cachePath.mkdirs()
    val file = File(cachePath, "shared_image.png")
    FileOutputStream(file).use { out ->
        this.compress(Bitmap.CompressFormat.PNG, 100, out)
    }
    return FileProvider.getUriForFile(
        context,
        "${context.packageName}.fileprovider",
        file
    )
}