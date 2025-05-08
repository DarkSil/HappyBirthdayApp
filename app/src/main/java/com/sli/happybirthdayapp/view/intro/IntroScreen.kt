@file:OptIn(ExperimentalMaterial3Api::class)

package com.sli.happybirthdayapp.view.intro

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sli.happybirthdayapp.R
import com.sli.happybirthdayapp.presentation.IntroViewModel
import com.sli.happybirthdayapp.view.ContinueShareButton
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch
import java.util.Locale

object IntroScreen {
    const val route = "intro"
}

@OptIn(FlowPreview::class, ExperimentalMaterial3Api::class)
@Composable
fun IntroScreen(viewModel: IntroViewModel, onClick: () -> Unit) {

    var initialDateMillis: Long? by rememberSaveable { mutableStateOf(null) }
    val datePickerState = remember(initialDateMillis) {
        DatePickerState(
            locale = Locale.getDefault(),
            initialSelectedDateMillis = initialDateMillis,
            initialDisplayedMonthMillis = null,
            yearRange = 1900..2100,
            initialDisplayMode = DisplayMode.Picker,
            selectableDates = DatePickerTextField.PastDates()
        )
    }

    val context = LocalContext.current
    val showDialog = remember { mutableStateOf(false) }

    var name by rememberSaveable { mutableStateOf("") }
    val nameState = viewModel.loadSavedName(context).collectAsState(null)
    val dateState = viewModel.loadSavedDoB(context).collectAsState(null)

    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp, 12.dp)
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.spacedBy(24.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        LaunchedEffect(Unit) {
            launch {
                snapshotFlow { nameState.value }
                    .filter { it != null }
                    .collect {
                        name = it!!
                    }
            }
            launch {
                snapshotFlow { dateState.value }
                    .filter { it != null }
                    .collect {
                        initialDateMillis = it
                    }
            }
            launch {
                snapshotFlow { name }
                    .debounce(300)
                    .collect {
                        viewModel.saveName(context, it)
                    }
            }
        }

        LaunchedEffect(datePickerState) {
            snapshotFlow { datePickerState.selectedDateMillis }
                .filter { it != null }
                .collect {
                    viewModel.saveDateOfBirth(context, it!!)
                }
        }

        val isButtonEnabled by remember(datePickerState) {
            derivedStateOf {
                name.isNotBlank() && datePickerState.selectedDateMillis != null
            }
        }

        StyledTextField(stringResource(R.string.name), name) { name = it }
        DatePickerTextField(showDialog, datePickerState)
        ImageSelector(viewModel)
        ContinueShareButton(
            text = stringResource(R.string.show_birthday_screen),
            enabled = isButtonEnabled
        ) {
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