package com.hermes.nextevent.presentation.eventdetail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.hermes.nextevent.presentation.eventdetail.components.CheckButton
import com.hermes.nextevent.util.EventImage

@Composable
fun EventDetail(
    viewModel: EventDetailViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (state.error.isNotBlank()) {
            Text(
                text = state.error,
                color = MaterialTheme.colorScheme.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
            )
        }
        if (state.isLoading) {
            CircularProgressIndicator()
        }
        state.event?.let{event ->
            EventImage(
                url = event.image,
                modifier = Modifier
                    .fillMaxWidth()
            )
            Text(
                text = event.title,
                style = MaterialTheme.typography.headlineSmall,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(8.dp, 12.dp, 4.dp, 8.dp)
            )
            Text(
                text = event.getFormattedDate(),
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Left,
                modifier = Modifier.padding(
                    start = 8.dp,
                    bottom = 8.dp)
            )
            Text(
                text = event.description,
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Left,
                overflow = TextOverflow.Visible,
                modifier = Modifier.padding(
                    start = 8.dp,
                    top = 12.dp,
                    bottom = 8.dp)
            )
            CheckButton(
                onButtonClick = {},
                modifier = Modifier
                    .width(200.dp)
                    .padding(4.dp)
            )
        }



    }
}