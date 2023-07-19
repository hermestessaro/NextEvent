package com.hermes.nextevent.presentation.navigation

sealed class Destination(val route: String) {
    object EventFeedScreen: Destination("EventFeed")
    object EventDetailScreen: Destination("event/{eventId}"){
        fun createRoute(eventId: String): String {
            return "event/$eventId"
        }
    }
}
