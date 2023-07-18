package com.hermes.nextevent.presentation.eventdetail

import com.hermes.nextevent.data.remote.model.Event

data class EventDetailState(
    val isLoading: Boolean = false,
    val event: Event? = null,
    val error: String = ""
)