package com.sli.happybirthdayapp.view.congrats

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sli.happybirthdayapp.R
import com.sli.happybirthdayapp.presentation.SharedViewModel
import com.sli.happybirthdayapp.utils.RandomBackground
import com.sli.happybirthdayapp.utils.px
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun ImagePlaceholder(
    background: RandomBackground,
    viewModel: SharedViewModel
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(50.dp, 0.dp)
    ) {
        val context = LocalContext.current
        val launcher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.GetContent()
        ) { uri: Uri? ->
            uri?.let {
                viewModel.saveUri(context, it)
            }
        }

        val imageBitmap by viewModel.savedBitmapState.collectAsState()

        LaunchedEffect(Unit) {
            viewModel.loadFromCache(context)
        }

        // Here I personally wanted to use Constraints to position the element at 45 degree on the
        // edge of a circle, but I found a smoother way which is moving the element to desired
        // position using offset and some math

        var size by remember { mutableStateOf(IntSize.Zero) }
        var imageSize by remember { mutableStateOf(IntSize.Zero) }

        val borderWidth = 7f
        // Aspect ratio is 1:1 so I can use any side as radius
        val radius = size.width/2 - (borderWidth/2).px
        val radians = Math.toRadians(-45.0)
        val offsetX = (radius * cos(radians))
        val offsetY = (radius * sin(radians))

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .sizeIn(
                    maxWidth = 222.dp,
                    maxHeight = 222.dp
                )
                .aspectRatio(1f / 1f)
                .clip(CircleShape)
                .background(background.placeholderColor)
                .border(
                    BorderStroke(borderWidth.dp, background.borderColor),
                    CircleShape
                )
                .onGloballyPositioned {
                    size = it.size
                }
        ) {
            if (imageBitmap != null) {
                Image(
                    bitmap = imageBitmap!!,
                    contentDescription = stringResource(R.string.your_image),
                    modifier = Modifier
                        .fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            } else {
                Image(
                    painter = painterResource(R.drawable.image_placeholder),
                    contentDescription = stringResource(R.string.image_placeholder),
                    modifier = Modifier
                        .size(100.dp),
                    colorFilter = ColorFilter.tint(background.borderColor),
                    contentScale = ContentScale.Fit
                )
            }
        }

        if (imageBitmap == null) {
            CameraImage(
                background = background,
                modifier = Modifier
                    .offset { IntOffset(offsetX.toInt(), offsetY.toInt()) }
                    .onGloballyPositioned {
                        imageSize = it.size
                    }
            ) {
                launcher.launch("image/*")
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    Scaffold {
        ImagePlaceholder(
            RandomBackground.getRandomBackground(),
            viewModel()
        )
    }
}