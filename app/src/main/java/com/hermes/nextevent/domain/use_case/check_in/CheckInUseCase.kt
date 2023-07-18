package com.hermes.nextevent.domain.use_case.check_in

import com.hermes.nextevent.data.remote.model.CheckinModel
import com.hermes.nextevent.data.remote.model.Event
import com.hermes.nextevent.domain.repository.EventRepository
import com.hermes.nextevent.util.NetworkResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CheckInUseCase @Inject constructor(
    private val repository: EventRepository
) {

    operator fun invoke(checkinModel: CheckinModel): Flow<NetworkResult<Any>> = flow {
        try {
            emit(NetworkResult.Loading())
            val response = repository.doCheckIn(checkinModel)
            emit(NetworkResult.Success(response))
        } catch (e: HttpException) {
            emit(NetworkResult.Error(e.localizedMessage ?: "Um erro ocorreu."))
        } catch (e: IOException) {
            emit(NetworkResult.Error("Não foi possível conectar ao servidor."))
        }
    }
}