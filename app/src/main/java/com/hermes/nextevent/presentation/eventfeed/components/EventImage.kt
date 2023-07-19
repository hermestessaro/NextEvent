package com.hermes.nextevent.presentation.eventfeed.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage

@Composable
fun EventImage(
    url: String,
    modifier: Modifier,
    contentScale: ContentScale = ContentScale.FillWidth
) {
    AsyncImage(model = url, contentDescription = null, modifier = modifier, contentScale = contentScale)
}