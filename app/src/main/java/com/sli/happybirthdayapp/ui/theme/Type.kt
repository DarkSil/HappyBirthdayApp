package com.sli.happybirthdayapp.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.sli.happybirthdayapp.R

val bentonsansFont = FontFamily(
    Font(R.font.bentonsans_regular)
)

val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = bentonsansFont,
        fontWeight = FontWeight.Bold,
        fontSize = 21.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = bentonsansFont,
        fontWeight = FontWeight.Normal,
        fontSize = 21.sp
    ),
    labelSmall = TextStyle(
        fontFamily = bentonsansFont,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    )
)