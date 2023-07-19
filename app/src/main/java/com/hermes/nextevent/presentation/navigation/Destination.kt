package com.hermes.nextevent.presentation.navigation

sealed class Destination(val route: String) {
    object EventFeedScreen: Destination("event_feed")
    object EventDetailScreen: Destination("event_detail")
}
