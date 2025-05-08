package com.sli.happybirthdayapp.view.intro

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sli.happybirthdayapp.R
import com.sli.happybirthdayapp.presentation.IntroViewModel
import com.sli.happybirthdayapp.ui.theme.Black
import com.sli.happybirthdayapp.ui.theme.NeutralForegroundColor
import com.sli.happybirthdayapp.ui.theme.White70

@Composable
fun ImageSelector(viewModel: IntroViewModel) {
    val context = LocalContext.current

    val imageBitmap by viewModel.savedBitmapState.collectAsState()

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            viewModel.saveUri(context, it)
        }
    }

    Column(
        verticalArrangement = Arrangement.spacedBy(6.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clickable {
                launcher.launch("image/*")
            }
    ){

        LaunchedEffect(Unit) {
            if (imageBitmap == null) {
                viewModel.loadFromCache(context)
            }
        }

        if (imageBitmap != null) {
            imageBitmap?.let {
                Card(
                    shape = CircleShape,
                    elevation = CardDefaults.cardElevation(0.dp,0.dp,0.dp,0.dp,0.dp,0.dp),
                    colors = CardDefaults.cardColors(
                        Color.Transparent,
                        Color.Transparent,
                        Color.Transparent,
                        Color.Transparent
                    ),
                    border = BorderStroke(1.5.dp, White70)
                ) {
                    Image(
                        bitmap = it,
                        contentDescription = stringResource(R.string.image_placeholder),
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.size(100.dp)
                    )
                }
            }
        } else {
            Image(
                painter = painterResource(R.drawable.image_placeholder),
                contentDescription = stringResource(R.string.image_placeholder),
                colorFilter = ColorFilter.tint(NeutralForegroundColor),
                modifier = Modifier.size(100.dp)
            )
            Text(
                text = stringResource(R.string.press_to_select),
                style = MaterialTheme.typography.labelSmall,
                fontSize = 12.sp,
                color = Black
            )
        }
    }
}

@Preview
@Composable
private fun Preview() {
    ImageSelector(viewModel())
}