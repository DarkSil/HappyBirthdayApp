@file:OptIn(ExperimentalMaterial3Api::class)

package com.sli.happybirthdayapp.view.intro

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.sli.happybirthdayapp.R
import com.sli.happybirthdayapp.utils.toDateString
import java.util.Calendar

@Composable
fun DatePickerTextField(
    showDialog: MutableState<Boolean>,
    datePickerState: DatePickerState
) {
    Box(
        modifier = Modifier.clickable(
            indication = null,
            interactionSource = remember { MutableInteractionSource() }
        ) {
            showDialog.value = true
        }
    ) {
        StyledTextField(
            label = stringResource(R.string.date_of_birth),
            value = datePickerState.selectedDateMillis?.toDateString() ?: "",
            readOnly = true
        ) {}
    }
}

@Preview
@Composable
private fun Preview() {
    val showDialog = remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState()

    DatePickerTextField(showDialog, datePickerState)
}

object DatePickerTextField {
    fun PastDates(): SelectableDates {
        return object : SelectableDates {
            override fun isSelectableDate(utcTimeMillis: Long): Boolean {
                return utcTimeMillis <= System.currentTimeMillis()
            }

            override fun isSelectableYear(year: Int): Boolean {
                return year <= Calendar.getInstance().get(Calendar.YEAR)
            }
        }
    }
}