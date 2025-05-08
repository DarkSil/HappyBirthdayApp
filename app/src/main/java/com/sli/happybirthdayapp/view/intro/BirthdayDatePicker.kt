@file:OptIn(ExperimentalMaterial3Api::class)

package com.sli.happybirthdayapp.view.intro

import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.sli.happybirthdayapp.R


@Composable
fun BirthdayDatePicker(
    showDialog: MutableState<Boolean>,
    datePickerState: DatePickerState
) {

    /*
        I decided to use here an experimental DatePicker just to avoid any of "xml-based" views
        so I can proceed with "Jetpack Compose" structure only. In real case I would check the
        usability and functionality for possible issues before integrating into project.
     */

    if (showDialog.value) {
        DatePickerDialog(
            onDismissRequest = { showDialog.value = false },
            confirmButton = {
                TextButton(onClick = { showDialog.value = false }) {
                    Text(stringResource(R.string.select), style = MaterialTheme.typography.bodyLarge)
                }
            },
            dismissButton = {
                TextButton(onClick = { showDialog.value = false }) {
                    Text(stringResource(R.string.cancel), style = MaterialTheme.typography.bodyLarge)
                }
            },
        ) {
            DatePicker(datePickerState)
        }
    }
}

@Preview
@Composable
private fun Preview() {
    val showDialog = remember {
        mutableStateOf(true)
    }

    val datePickerState = rememberDatePickerState()
    BirthdayDatePicker(showDialog, datePickerState)
}