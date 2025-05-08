@file:OptIn(ExperimentalMaterial3Api::class)

package com.sli.happybirthdayapp.view.intro

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sli.happybirthdayapp.R
import com.sli.happybirthdayapp.presentation.IntroViewModel
import com.sli.happybirthdayapp.view.ContinueShareButton

object IntroScreen {
    const val route = "intro"
}

@Composable
fun IntroScreen(viewModel: IntroViewModel, onClick: () -> Unit) {
    val scrollState = rememberScrollState()

    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = /* TODO GET DOB MILLIS FROM DATASTORE */ null,
        selectableDates = DatePickerTextField.PastDates()
    )
    val showDialog = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp, 12.dp)
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.spacedBy(24.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        // TODO Add all the texts to the Strings and commit
        // TODO Get name from DataStore
        var name by rememberSaveable { mutableStateOf("") }
        StyledTextField(stringResource(R.string.name), name) { name = it}
        DatePickerTextField(showDialog, datePickerState)
        ImageSelector(viewModel)

        val isButtonEnabled by remember {
            derivedStateOf {
                name.isNotBlank() && datePickerState.selectedDateMillis != null
            }
        }
        ContinueShareButton(stringResource(R.string.show_birthday_screen), isButtonEnabled) {
            onClick()
        }
    }

    BirthdayDatePicker(showDialog, datePickerState)
}

@Preview
@Composable
private fun Preview() {
    IntroScreen(viewModel()) {  }
}