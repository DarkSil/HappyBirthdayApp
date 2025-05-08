package com.sli.happybirthdayapp.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sli.happybirthdayapp.R
import com.sli.happybirthdayapp.ui.theme.ButtonColor
import com.sli.happybirthdayapp.ui.theme.White

@Composable
fun ContinueShareButton(
    modifier: Modifier = Modifier,
    text: String,
    enabled: Boolean = true,
    showShareIcon: Boolean = false,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        enabled = enabled,
        shape = RoundedCornerShape(21.dp),
        colors = ButtonColors(
            containerColor = ButtonColor,
            disabledContainerColor = ButtonColor.copy(0.3f),
            contentColor = White,
            disabledContentColor = White
        ),
        contentPadding = if (showShareIcon) {
            PaddingValues(21.dp, 13.dp, 10.dp, 10.dp)
        } else {
            ButtonDefaults.ContentPadding
        },
        modifier = Modifier
            .defaultMinSize(minHeight = 42.dp)
            .then(modifier)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = text,
                style = MaterialTheme.typography.labelSmall
            )
            if (showShareIcon) {
                Icon(
                    painterResource(R.drawable.ic_share),
                    stringResource(R.string.share),
                    modifier = Modifier
                        .padding(bottom = 3.dp)
                        .size(21.dp, 21.dp)
                )
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    ContinueShareButton(
        text = stringResource(R.string.share_the_news),
        showShareIcon = true
    ) { }
}