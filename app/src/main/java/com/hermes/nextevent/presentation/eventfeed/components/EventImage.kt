package com.hermes.nextevent.presentation.eventfeed.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import com.hermes.nextevent.R

@Composable
fun EventImage(
    url: String,
    modifier: Modifier,
    contentScale: ContentScale = ContentScale.FillWidth
) {
    AsyncImage(
        model = url,
        contentDescription = null,
        modifier = modifier,
        contentScale = contentScale,
        error = painterResource(id = R.drawable.no_photo)
    )
}