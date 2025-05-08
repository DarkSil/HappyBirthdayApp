package com.sli.happybirthdayapp.view.congrats

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sli.happybirthdayapp.R
import com.sli.happybirthdayapp.ui.theme.White
import com.sli.happybirthdayapp.utils.RandomBackground

@Composable
fun CameraImage(
    background: RandomBackground,
    modifier: Modifier? = null,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .then(modifier ?: Modifier)
            .clip(CircleShape)
            .background(background.borderColor)
            .size(36.dp)
            .padding(9.dp, 8.dp, 4.dp, 6.dp)
            .clickable(
                indication = null,
                interactionSource = MutableInteractionSource(),
                onClick = onClick
            )
    ) {
        Image(
            painter = painterResource(R.drawable.ic_camera),
            contentDescription = stringResource(R.string.select_image),
            modifier = Modifier
                .size(23.dp),
            colorFilter = ColorFilter.tint(White),
            contentScale = ContentScale.Fit
        )
    }
}

@Preview
@Composable
private fun Preview() {
    CameraImage(RandomBackground.getRandomBackground()) {}
}