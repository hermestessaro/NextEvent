package com.hermes.nextevent.domain.use_case.get_event

import com.hermes.nextevent.data.remote.model.Event
import com.hermes.nextevent.domain.repository.EventRepository
import com.hermes.nextevent.util.NetworkResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetEventByIdUseCase @Inject constructor(
    private val repository: EventRepository
) {

    operator fun invoke(eventId: String): Flow<NetworkResult<Event>> = flow {
        try {
            emit(NetworkResult.Loading())
            val event = repository.getEventById(eventId)
            emit(NetworkResult.Success(event))
        } catch (e: HttpException) {
            emit(NetworkResult.Error(e.localizedMessage ?: "An error occurred"))
        } catch (e: IOException) {
            emit(NetworkResult.Error("Couldn't reach server."))
        }
    }
}