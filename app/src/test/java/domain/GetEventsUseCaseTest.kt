package domain

import com.hermes.nextevent.data.remote.model.Event
import com.hermes.nextevent.domain.repository.EventRepository
import com.hermes.nextevent.domain.use_case.get_events.GetEventsUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import java.io.IOException

@OptIn(ExperimentalCoroutinesApi::class)
class GetEventsUseCaseTest {
    private val repository: EventRepository = mockk(relaxed = true)
    private lateinit var getEventsUseCase: GetEventsUseCase

    @Before
    fun setUp() {
        getEventsUseCase = GetEventsUseCase(repository)
    }
    @Test
    fun `if repo successfully returns events, use case also returns them`() = runTest {
        coEvery { repository.getEvents() } returns getList()
        val response = getEventsUseCase().last()
        assert(response.data?.first()?.id == "1")
    }

    @Test
    fun `if repo fails to return event, use case should accuse error`() = runTest {
        coEvery { repository.getEvents() } throws IOException()
        val response = getEventsUseCase().last()
        assert(!response.message.isNullOrEmpty())
    }

    private fun getList(): List<Event> {
        val event1 = Event(
            listOf(),
            date = 1534784400000,
            description = "desc1",
            image = "testimage",
            longitude = -51.2146267,
            latitude = -30.0392981,
            price = 29.99f,
            title = "title",
            id = "1"
        )
        val event2 = Event(
            listOf(),
            date = 1534784400000,
            description = "desc2",
            image = "testimage",
            longitude = -51.2146267,
            latitude = -30.0392981,
            price = 29.99f,
            title = "title2",
            id = "2"
        )
        val event3 = Event(
            listOf(),
            date = 1534784400000,
            description = "desc3",
            image = "testimage",
            longitude = -51.2146267,
            latitude = -30.0392981,
            price = 29.99f,
            title = "title3",
            id = "3"
        )
        return listOf(event1, event2, event3)
    }
}