package com.hermes.nextevent.feature_list.domain.use_case

import com.hermes.nextevent.domain.use_case.get_events.GetEventsUseCase
import com.hermes.nextevent.feature_list.util.FakeEventRepository
import com.hermes.nextevent.util.NetworkResult
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetEventsUseCaseTest {
    private lateinit var fakeEventRepository: FakeEventRepository
    private lateinit var getEventsUseCase: GetEventsUseCase


    @Before
    fun setUp() {
        fakeEventRepository = FakeEventRepository()
        getEventsUseCase = GetEventsUseCase(fakeEventRepository)
    }

    @Test
    fun `get event list, correct list must return`() = runBlocking {
        val events = getEventsUseCase()
        var response = events.first()
        assert(response is NetworkResult.Loading)
        response = events.last()
        assert(response is NetworkResult.Success)
        assert(response.data?.first()?.title == "title1")
    }

}