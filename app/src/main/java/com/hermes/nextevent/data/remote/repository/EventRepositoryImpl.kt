package com.hermes.nextevent.data.remote.repository

import com.hermes.nextevent.data.remote.EventApi
import com.hermes.nextevent.data.remote.model.CheckinModel
import com.hermes.nextevent.data.remote.model.Event
import com.hermes.nextevent.domain.repository.EventRepository
import okhttp3.ResponseBody
import retrofit2.Response
import javax.inject.Inject

class EventRepositoryImpl @Inject constructor(
    private val api: EventApi
): EventRepository {

    override suspend fun getEvents(): List<Event> {
        return api.getEvents()
    }

    override suspend fun getEventById(eventId: String): Event {
        return api.getEventById(eventId)
    }

    override suspend fun doCheckIn(checkinModel: CheckinModel): Response<ResponseBody> {
        return api.doCheckin(checkinModel)
    }
}