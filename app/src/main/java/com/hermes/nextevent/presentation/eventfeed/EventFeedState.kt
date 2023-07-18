package com.hermes.nextevent.presentation.eventfeed

import com.hermes.nextevent.data.remote.model.Event

data class EventFeedState(
    val isLoading: Boolean = false,
    val events: List<Event> = emptyList(),
    val error: String = ""
)
