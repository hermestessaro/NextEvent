package com.hermes.nextevent.presentation.eventdetail


data class CheckInState(
    val isLoading: Boolean = false,
    val code: String = "",
    val error: String = ""
)
