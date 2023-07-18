package com.hermes.nextevent.domain.repository

import com.hermes.nextevent.data.remote.model.CheckinModel
import com.hermes.nextevent.data.remote.model.Event

interface EventRepository {

    suspend fun getEvents(): List<Event>

    suspend fun getEventById(eventId: String): Event

    suspend fun doCheckin(checkinModel: CheckinModel)
}