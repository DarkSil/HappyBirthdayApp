package com.sli.happybirthdayapp.view.congrats

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sli.happybirthdayapp.R
import com.sli.happybirthdayapp.model.TimeFrame
import com.sli.happybirthdayapp.presentation.CongratsViewModel
import com.sli.happybirthdayapp.ui.theme.HeaderTextColor

@Composable
fun CounterText(
    modifier: Modifier = Modifier,
    viewModel: CongratsViewModel
) {
    val context = LocalContext.current

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(56.dp, 20.dp, 56.dp, 0.dp)
            .then(modifier)
    ) {
        val name by viewModel.loadSavedName(context).collectAsState(null)
        val dateOfBirth by viewModel.loadSavedDoB(context).collectAsState(null)


        Text(
            text = stringResource(
                R.string.today_is,
                name ?: stringResource(R.string.baby)
            ).uppercase(),
            style = MaterialTheme.typography.bodyLarge,
            color = HeaderTextColor,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
        )

        if (dateOfBirth != null) {
            val value = viewModel.getTimeFrame(dateOfBirth!!)
            val ageText = when (value) {
                is TimeFrame.Month -> stringResource(R.string.month_old)
                is TimeFrame.Year -> stringResource(R.string.years_old)
            }.uppercase()

            CounterAnimated(value.value)
            Text(
                text = ageText,
                style = MaterialTheme.typography.bodyLarge,
                color = HeaderTextColor,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
    }
}

@Preview
@Composable
private fun Preview() {
    CounterText(
        viewModel = viewModel()
    )
}