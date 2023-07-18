package com.hermes.nextevent.data.remote

import com.hermes.nextevent.data.remote.model.CheckinModel
import com.hermes.nextevent.data.remote.model.Event
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface EventApi {
    @GET("events")
    suspend fun getEvents(): List<Event>

    @GET("events/{eventId}")
    suspend fun getEventById(@Path("eventId") eventId: String): Event

    @POST("checkin")
    suspend fun doCheckin(@Body checkinModel: CheckinModel)
}