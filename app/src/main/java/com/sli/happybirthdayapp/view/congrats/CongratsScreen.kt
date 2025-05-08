package com.sli.happybirthdayapp.view.congrats

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sli.happybirthdayapp.R
import com.sli.happybirthdayapp.presentation.CongratsViewModel
import com.sli.happybirthdayapp.utils.RandomBackground
import com.sli.happybirthdayapp.view.ContinueShareButton

object CongratsScreen {
    const val route = "congrats"
}

@Composable
fun CongratsScreen(viewModel: CongratsViewModel) {
    val background by rememberSaveable(
        saver = Saver(
            save = { randomBackground -> randomBackground.value },
            restore = { mutableStateOf(it) }
        )
    ) {
        mutableStateOf(RandomBackground.getRandomBackground())
    }

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(background.color)
    ) {

        val (button, branding, image, counter) = createRefs()

        Column(
            verticalArrangement = Arrangement.Bottom,
            modifier = Modifier
                .constrainAs(image) {
                    bottom.linkTo(branding.top)
                    top.linkTo(counter.bottom)
                    height = Dimension.fillToConstraints
                }
        ) {
            ImagePlaceholder(
                background = background,
                viewModel = viewModel
            )
        }
        Image(
            painter = painterResource(background.image),
            contentDescription = stringResource(R.string.image_background),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
        )
        ContinueShareButton(
            text = stringResource(R.string.share_the_news),
            showShareIcon = true,
            modifier = Modifier
                .constrainAs(button) {
                    bottom.linkTo(parent.bottom, 53.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        ) {
            // TODO Share
        }
        Image(
            painter = painterResource(R.drawable.branding),
            contentDescription = stringResource(R.string.nanit_branding_image),
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .size(60.dp, 20.dp)
                .constrainAs(branding) {
                    bottom.linkTo(button.top, 50.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )
        CounterText(
            viewModel = viewModel,
            modifier = Modifier
                .constrainAs(counter) {
                    top.linkTo(parent.top)
                    bottom.linkTo(image.top)
                    height = Dimension.fillToConstraints
                }
                .fillMaxWidth()
        )
    }
}

@Preview
@Composable
private fun Preview() {
    CongratsScreen(viewModel())
}