package com.hermes.nextevent.feature_list.domain.use_case

import com.hermes.nextevent.domain.use_case.get_event.GetEventByIdUseCase
import com.hermes.nextevent.feature_list.util.FakeEventRepository
import com.hermes.nextevent.util.NetworkResult
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetEventByIdUseCaseTest {
    private lateinit var fakeEventRepository: FakeEventRepository
    private lateinit var getEventByIdUseCase: GetEventByIdUseCase

    @Before
    fun setUp() {
        fakeEventRepository = FakeEventRepository()
        getEventByIdUseCase = GetEventByIdUseCase(fakeEventRepository)
    }

    @Test
    fun `get event by id, correct event must return`() = runBlocking {
        val event = getEventByIdUseCase("0")
        val response = event.last()
        assert(response is NetworkResult.Success)
        assert(response.data?.id == "0")
    }

    @Test
    fun `get event by id and doesn't find it`() = runBlocking {
        val event = getEventByIdUseCase("6")
        val response = event.last()
        assert(response.data == null)
    }

}