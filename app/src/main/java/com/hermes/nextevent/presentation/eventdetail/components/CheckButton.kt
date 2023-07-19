package com.hermes.nextevent.presentation.eventdetail.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CheckButton(
    onButtonClick: () -> Unit,
    modifier: Modifier
){
    Button(
        onClick = onButtonClick,
        modifier = modifier
    ) {
        Icon(imageVector = Icons.Default.CheckCircle, contentDescription = "Check In")
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = "FAZER CHECK IN",
            style = MaterialTheme.typography.labelLarge
        )
    }
}