package com.sli.happybirthdayapp.model

sealed class TimeFrame(
    open val value: Int
) {
    data class Year(
        override val value: Int
    ) : TimeFrame(value)

    data class Month(
        override val value: Int
    ) : TimeFrame(value)
}