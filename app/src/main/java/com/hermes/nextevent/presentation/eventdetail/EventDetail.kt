package com.hermes.nextevent.presentation.eventdetail

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hermes.nextevent.R
import com.hermes.nextevent.presentation.eventdetail.components.CheckInDialog
import com.hermes.nextevent.presentation.eventdetail.components.EventDetailButton
import com.hermes.nextevent.presentation.eventdetail.components.showSnackbar
import com.hermes.nextevent.util.EventImage

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventDetail(
    viewModel: EventDetailViewModel = hiltViewModel()
) {
    val eventState = viewModel.eventState.value
    val checkInState = viewModel.checkInState.value
    val showDialog = remember { mutableStateOf(false) }
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (eventState.error.isNotBlank()) {
                    Text(
                        text = eventState.error,
                        color = MaterialTheme.colorScheme.error,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp)
                    )
                }
                if (eventState.isLoading || checkInState.isLoading) {
                    CircularProgressIndicator()
                }
                if(checkInState.error.isNotBlank()) {
                    showSnackbar(snackbarHostState = snackbarHostState, text = checkInState.error)
                }
                if(!checkInState.code.isNullOrEmpty()) {
                    showSnackbar(snackbarHostState = snackbarHostState, text = stringResource(R.string.checkIn_success))
                }
                eventState.event?.let{event ->
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
                    EventDetailButton(
                        onButtonClick = { showDialog.value = true },
                        modifier = Modifier
                            .width(200.dp)
                            .padding(4.dp),
                        text = stringResource(R.string.do_checkIn),
                        icon = Icons.Default.CheckCircle
                    )

                    val shareableText = stringResource(
                        R.string.shareable_text_template, event.title, event.getFormattedDate(), event.description
                    )
                    val context = LocalContext.current
                    EventDetailButton(
                        onButtonClick = {
                            viewModel.shareEvent(
                                shareableText,
                                context
                            )
                        },
                        modifier = Modifier
                            .width(200.dp)
                            .padding(4.dp),
                        text = stringResource(R.string.share),
                        icon = Icons.Default.Share
                    )

                    CheckInDialog(
                        showDialog = showDialog.value,
                        onDismiss = { showDialog.value = false },
                        onConfirm = { name, email ->
                            viewModel.doCheckIn(name, email)
                            showDialog.value = false
                        }
                    )
                }
            }
        },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    )

}