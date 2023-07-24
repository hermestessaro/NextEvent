package com.hermes.nextevent.presentation.eventfeed

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.hermes.nextevent.presentation.navigation.Destination
import com.hermes.nextevent.presentation.eventfeed.components.EventFeedItem

@Composable
fun EventFeed(
    navController: NavController,
    viewModel: EventFeedViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    Box(modifier = Modifier.fillMaxSize()) {
        if (state.error.isNotBlank()) {
            Text(
                text = state.error,
                color = MaterialTheme.colorScheme.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .align(Alignment.Center)
            )
        }
        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(horizontal = 4.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
            items(state.events) { event ->
                EventFeedItem(
                    event = event,
                    onItemClick = {
                        navController.navigate(Destination.EventDetailScreen.route + "/${event.id}")
                    }
                )
            }
        }
    }


}