package com.hermes.nextevent.domain.repository

import com.hermes.nextevent.data.remote.model.CheckinModel
import com.hermes.nextevent.data.remote.model.Event
import okhttp3.ResponseBody
import retrofit2.Response

interface EventRepository {

    suspend fun getEvents(): List<Event>

    suspend fun getEventById(eventId: String): Event

    suspend fun doCheckIn(checkinModel: CheckinModel): Response<ResponseBody>
}