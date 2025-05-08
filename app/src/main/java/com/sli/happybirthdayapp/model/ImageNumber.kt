package com.sli.happybirthdayapp.model

import com.sli.happybirthdayapp.R

sealed class ImageNumber(val image: Int) {
    
    data object Zero : ImageNumber(R.drawable.zero)

    data object One : ImageNumber(R.drawable.one)

    data object Two : ImageNumber(R.drawable.two)

    data object Three : ImageNumber(R.drawable.three)

    data object Four : ImageNumber(R.drawable.four)

    data object Five : ImageNumber(R.drawable.five)

    data object Six : ImageNumber(R.drawable.six)

    data object Seven : ImageNumber(R.drawable.seven)

    data object Eight : ImageNumber(R.drawable.eight)

    data object Nine : ImageNumber(R.drawable.nine)

    class UnknownCharException : Exception()

    companion object {
        fun getImageNumbers(value: Int): List<ImageNumber> {
            val textNumber = value.toString()
            val list = mutableListOf<ImageNumber>()
            for (element in textNumber) {
                list.add(getCorrespondedImage(element))
            }
            return list
        }

        private fun getCorrespondedImage(char: Char) : ImageNumber {
            return when (char) {
                '0' -> Zero
                '1' -> One
                '2' -> Two
                '3' -> Three
                '4' -> Four
                '5' -> Five
                '6' -> Six
                '7' -> Seven
                '8' -> Eight
                '9' -> Nine
                else -> throw UnknownCharException()
            }
        }
    }
}