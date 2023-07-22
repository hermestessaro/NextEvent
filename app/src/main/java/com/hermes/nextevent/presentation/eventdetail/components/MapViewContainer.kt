package com.hermes.nextevent.presentation.eventdetail.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.hermes.nextevent.data.remote.model.Event

@Composable
fun MapViewContainer(event: Event) {
    val location = LatLng(event.latitude, event.longitude)
    val locationState = MarkerState(position = location)
    val cameraPositionState = rememberCameraPositionState{
        position = CameraPosition.fromLatLngZoom(location, 15f)
    }

    GoogleMap(
        modifier = Modifier.fillMaxSize().height(200.dp),
        cameraPositionState = cameraPositionState
    ) {
        Marker(
            state = locationState,
            title = event.title
        )
    }
}