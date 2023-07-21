package com.hermes.nextevent.presentation.eventdetail

import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hermes.nextevent.data.remote.model.CheckinModel
import com.hermes.nextevent.domain.use_case.check_in.CheckInUseCase
import com.hermes.nextevent.domain.use_case.get_event.GetEventByIdUseCase
import com.hermes.nextevent.domain.use_case.share.ShareUseCase
import com.hermes.nextevent.util.Constants
import com.hermes.nextevent.util.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class EventDetailViewModel @Inject constructor(
    private val getEventByIdUseCase: GetEventByIdUseCase,
    private val checkInUseCase: CheckInUseCase,
    private val shareUseCase: ShareUseCase,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _eventState = mutableStateOf(EventDetailState())
    val eventState: State<EventDetailState> = _eventState
    private val _checkInState = mutableStateOf(CheckInState())
    val checkInState: State<CheckInState> = _checkInState

    init {
        savedStateHandle.get<String>(Constants.PARAM_EVENT_ID)?.let{ eventId ->
            getEventById(eventId)
        }
    }

    private fun getEventById(eventId: String) {
        getEventByIdUseCase(eventId).onEach { networkResult ->
            when(networkResult){
                is NetworkResult.Success -> {
                    _eventState.value = EventDetailState(event = networkResult.data)
                }
                is NetworkResult.Error -> {
                    _eventState.value = EventDetailState(
                        error = networkResult.message ?: "Um erro inesperado aconteceu"
                    )
                }
                is NetworkResult.Loading -> {
                    _eventState.value = EventDetailState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun doCheckIn(name: String, email: String) {
        _eventState.value.event?.let { event ->
            val checkInModel = CheckinModel(event.id, name, email)
            checkInUseCase(checkInModel).onEach { networkResult ->
                when(networkResult){
                        is NetworkResult.Success -> {
                        _checkInState.value = CheckInState(code = networkResult.data)
                    }
                    is NetworkResult.Error -> {
                        _checkInState.value = CheckInState(
                            error = networkResult.message ?: "Um erro inesperado aconteceu"
                        )
                    }
                    is NetworkResult.Loading -> {
                        _checkInState.value = CheckInState(isLoading = true)
                    }
                }
            }.launchIn(viewModelScope)
        }
    }

    fun shareEvent(text: String, context: Context) {
        shareUseCase(text, context)
    }
}