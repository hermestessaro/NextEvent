package com.hermes.nextevent.presentation.eventdetail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hermes.nextevent.domain.use_case.get_event.GetEventByIdUseCase
import com.hermes.nextevent.util.Constants
import com.hermes.nextevent.util.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class EventDetailViewModel @Inject constructor(
    private val getEventByIdUseCase: GetEventByIdUseCase,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _state = mutableStateOf(EventDetailState())
    val state: State<EventDetailState> = _state

    init {
        savedStateHandle.get<String>(Constants.PARAM_EVENT_ID)?.let{ eventId ->
            getEventById(eventId)
        }
    }

    private fun getEventById(eventId: String) {
        getEventByIdUseCase(eventId).onEach { networkResult ->
            when(networkResult){
                is NetworkResult.Success -> {
                    _state.value = EventDetailState(event = networkResult.data)
                }
                is NetworkResult.Error -> {
                    _state.value = EventDetailState(
                        error = networkResult.message ?: "Um erro inesperado aconteceu"
                    )
                }
                is NetworkResult.Loading -> {
                    _state.value = EventDetailState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}