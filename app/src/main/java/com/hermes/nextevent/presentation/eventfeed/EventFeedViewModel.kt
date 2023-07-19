package com.hermes.nextevent.presentation.eventfeed

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hermes.nextevent.data.remote.model.Event
import com.hermes.nextevent.domain.use_case.get_events.GetEventsUseCase
import com.hermes.nextevent.util.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class EventFeedViewModel @Inject constructor(
    private val getEventsUseCase: GetEventsUseCase
): ViewModel() {

    private val _state = mutableStateOf(EventFeedState())
    val state: State<EventFeedState> = _state

    init {
        getEvents()
    }

    private fun getEvents() {
        getEventsUseCase().onEach { networkResult ->
            when(networkResult){
                is NetworkResult.Success -> {
                    _state.value = EventFeedState(events = networkResult.data ?: emptyList())
                }
                is NetworkResult.Error -> {
                    _state.value = EventFeedState(
                        error = networkResult.message ?: "Um erro inesperado aconteceu"
                    )
                }
                is NetworkResult.Loading -> {
                    _state.value = EventFeedState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}