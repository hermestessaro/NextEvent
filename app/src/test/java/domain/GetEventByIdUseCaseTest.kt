package domain

import com.hermes.nextevent.data.remote.model.Event
import com.hermes.nextevent.domain.repository.EventRepository
import com.hermes.nextevent.domain.use_case.get_event.GetEventByIdUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import java.io.IOException

class GetEventByIdUseCaseTest {
    private val repository: EventRepository = mockk(relaxed = true)
    private lateinit var getEventByIdUseCase: GetEventByIdUseCase

    @Before
    fun setUp() {
        getEventByIdUseCase = GetEventByIdUseCase(repository)
    }

    @Test
    fun `if repo successfully returns event, use case also returns it`() = runTest {
        coEvery { repository.getEventById("1") } returns getEvent()
        val response = getEventByIdUseCase("1").last()
        assert(response.data?.title == "title")
    }

    @Test
    fun `if repo fails to return event, use case should accuse error`() = runTest {
        coEvery { repository.getEventById("1") } throws IOException()
        val response = getEventByIdUseCase("1").last()
        assert(!response.message.isNullOrEmpty())
    }

    private fun getEvent(): Event {
        return Event(
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
    }
}