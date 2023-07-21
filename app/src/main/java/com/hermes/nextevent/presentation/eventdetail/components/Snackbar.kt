package com.hermes.nextevent.presentation.eventdetail.components

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.launch

@Composable
fun showSnackbar(
    snackbarHostState: SnackbarHostState,
    text: String
) {
    val scope = rememberCoroutineScope()
    
    LaunchedEffect(Unit) {
        scope.launch {
            snackbarHostState.showSnackbar(
                message = text,
                actionLabel = "Fechar",
                duration = SnackbarDuration.Short
            )
        }
    }
}