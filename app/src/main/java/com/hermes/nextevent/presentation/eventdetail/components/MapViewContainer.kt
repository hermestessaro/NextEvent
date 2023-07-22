package com.hermes.nextevent.presentation.eventdetail.components

import android.os.Bundle
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
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
        position = CameraPosition.fromLatLngZoom(location, 10f)
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

    /*
    val mapView = rememberMapViewWithLifecycle()
    LaunchedEffect(mapView){
        mapView.getMapAsync { googleMap ->
            val location = LatLng(event.latitude, event.longitude)
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 15f))
            googleMap.addMarker(MarkerOptions().position(location).title(event.title))

            googleMap.uiSettings.isScrollGesturesEnabled = true
            googleMap.uiSettings.isZoomGesturesEnabled = true
            googleMap.uiSettings.isMapToolbarEnabled = true
        }
    }

    AndroidView(
        factory = {mapView},
        modifier = Modifier.fillMaxSize().height(200.dp)
    )
}

@Composable
fun rememberMapViewWithLifecycle(): MapView {
    val context = LocalContext.current
    val mapView = remember {
        MapView(context).apply{
            onCreate(Bundle())
        }
    }

    DisposableEffect(mapView) {
        onDispose {
            mapView.onDestroy()
        }
    }

    return mapView
}*/