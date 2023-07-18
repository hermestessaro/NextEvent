package com.hermes.nextevent.domain.use_case.get_events

import com.hermes.nextevent.data.remote.model.Event
import com.hermes.nextevent.domain.repository.EventRepository
import com.hermes.nextevent.util.NetworkResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetEventsUseCase @Inject constructor(
    private val repository: EventRepository
) {

    operator fun invoke(): Flow<NetworkResult<List<Event>>> = flow {
        try {
            emit(NetworkResult.Loading())
            val events = repository.getEvents()
            emit(NetworkResult.Success(events))
        } catch (e: HttpException) {
            emit(NetworkResult.Error(e.localizedMessage ?: "An error occurred"))
        } catch (e: IOException) {
            emit(NetworkResult.Error("Couldn't reach server."))
        }
    }
}