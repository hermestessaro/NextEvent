package com.hermes.nextevent.feature_list.util

import com.hermes.nextevent.data.remote.model.CheckinModel
import com.hermes.nextevent.data.remote.model.Event
import com.hermes.nextevent.domain.repository.EventRepository
import okhttp3.ResponseBody
import retrofit2.Response

class FakeEventRepository : EventRepository {
    val event1 = Event(
        listOf(),
        1534784400000,
        "desc1",
        "http://lproweb.procempa.com.br/pmpa/prefpoa/seda_news/usu_img/Papel%20de%20Parede.png",
        -51.2146267,
        -30.0392981,
        29.99f,
        "title1",
        "0"
    )
    val event2 = Event(
        listOf(),
        1534784400000,
        "desc2",
        "http://lproweb.procempa.com.br/pmpa/prefpoa/seda_news/usu_img/Papel%20de%20Parede.png",
        -51.2146267,
        -30.0392981,
        29.99f,
        "title2",
        "1"
    )
    val event3 = Event(
        listOf(),
        1534784400000,
        "desc3",
        "http://lproweb.procempa.com.br/pmpa/prefpoa/seda_news/usu_img/Papel%20de%20Parede.png",
        -51.2146267,
        -30.0392981,
        29.99f,
        "title3",
        "2"
    )
    private val eventList = listOf(event1, event2, event3)

    override suspend fun getEvents(): List<Event> {
        return eventList
    }

    override suspend fun getEventById(eventId: String): Event? {
        return eventList.find { it.id == eventId}
    }

    override suspend fun doCheckIn(checkinModel: CheckinModel): Response<ResponseBody> {
        TODO("Not yet implemented")
    }

}