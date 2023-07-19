package com.hermes.nextevent.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.hermes.nextevent.presentation.eventdetail.EventDetail
import com.hermes.nextevent.presentation.eventfeed.EventFeed

@Composable
fun NavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Destination.EventFeedScreen.route,
        builder = {
            composable(route = Destination.EventFeedScreen.route) {
                EventFeed(navController)
            }
            composable(route = Destination.EventDetailScreen.route + "/{eventId}") {
                EventDetail()
            }

        })
}