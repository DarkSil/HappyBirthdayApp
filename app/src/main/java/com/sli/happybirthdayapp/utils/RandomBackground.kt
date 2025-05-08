package com.sli.happybirthdayapp.utils

import androidx.compose.ui.graphics.Color
import com.sli.happybirthdayapp.R
import com.sli.happybirthdayapp.ui.theme.BackgroundVariationOne
import com.sli.happybirthdayapp.ui.theme.BackgroundVariationThree
import com.sli.happybirthdayapp.ui.theme.BackgroundVariationTwo
import com.sli.happybirthdayapp.ui.theme.BorderOne
import com.sli.happybirthdayapp.ui.theme.BorderThree
import com.sli.happybirthdayapp.ui.theme.BorderTwo
import com.sli.happybirthdayapp.ui.theme.PlaceholderOne
import com.sli.happybirthdayapp.ui.theme.PlaceholderThree
import com.sli.happybirthdayapp.ui.theme.PlaceholderTwo
import java.io.Serializable

sealed class RandomBackground(
    val id: Int,
    val color: Color,
    val placeholderColor: Color,
    val borderColor: Color,
    val image: Int
) : Serializable {

    class Elephant : RandomBackground(
        0,
        BackgroundVariationOne,
        PlaceholderOne,
        BorderOne,
        R.drawable.bg_elephant
    )
    class Fox : RandomBackground(
        1,
        BackgroundVariationTwo,
        PlaceholderTwo,
        BorderTwo,
        R.drawable.bg_fox
    )
    class Pelican : RandomBackground(
        2,
        BackgroundVariationThree,
        PlaceholderThree,
        BorderThree,
        R.drawable.bg_pelican
    )

    companion object {
        fun getRandomBackground() : RandomBackground {
            val random = (Math.random() * 100).div(33).toInt()
            return when (random) {
                0 -> Elephant()
                1 -> Fox()
                else -> Pelican()
            }
        }
    }
}