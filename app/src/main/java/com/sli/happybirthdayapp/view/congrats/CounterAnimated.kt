package com.sli.happybirthdayapp.view.congrats

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sli.happybirthdayapp.R
import com.sli.happybirthdayapp.model.ImageNumber

@Composable
fun CounterAnimated(value: Int) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(22.dp, Alignment.CenterHorizontally),
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(0.dp, 13.dp, 0.dp, 14.dp)
    ) {
        Image(
            painter = painterResource(R.drawable.image_swirls),
            contentDescription = stringResource(R.string.swirls_image),
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .size(50.dp, 44.dp)
        )

        val numbersList = ImageNumber.getImageNumbers(value)
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(22.dp)
        ) {
            items(numbersList.size) {
                Image(
                    painter = painterResource(numbersList[it].image),
                    contentDescription = "",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .size(50.dp, 88.dp)
                        .clearAndSetSemantics {  }
                )
            }
        }

        Image(
            painter = painterResource(R.drawable.image_swirls),
            contentDescription = stringResource(R.string.swirls_image),
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .size(50.dp, 44.dp)
                .graphicsLayer { scaleX = -1f }
        )
    }
}

@Preview
@Composable
private fun Preview() {
    CounterAnimated(25)
}