package presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.SavedStateHandle
import com.hermes.nextevent.data.remote.model.CheckinModel
import com.hermes.nextevent.data.remote.model.Event
import com.hermes.nextevent.domain.use_case.check_in.CheckInUseCase
import com.hermes.nextevent.domain.use_case.get_event.GetEventByIdUseCase
import com.hermes.nextevent.domain.use_case.share.ShareUseCase
import com.hermes.nextevent.presentation.eventdetail.EventDetailViewModel
import com.hermes.nextevent.util.NetworkResult
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Response
import util.CoroutineTestRule

@OptIn(ExperimentalCoroutinesApi::class)
class EventDetailViewModelTest {
    @get:Rule
    val instantTask = InstantTaskExecutorRule()
    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    private val getEventByIdUseCase: GetEventByIdUseCase = mockk(relaxed = true)
    private val checkInUseCase: CheckInUseCase = mockk(relaxed = true)
    private val shareUseCase: ShareUseCase = mockk(relaxed = true)
    private val savedStateHandle: SavedStateHandle = mockk(relaxed = true)

    private lateinit var eventDetailViewModel: EventDetailViewModel

    @Before
    fun setUp(){
        every { savedStateHandle.get<String>("eventId") } returns "1"
    }

    @Test
    fun `should call getEventById on viewModel init`() = runTest {
        eventDetailViewModel = EventDetailViewModel(
            getEventByIdUseCase,
            checkInUseCase,
            shareUseCase,
            savedStateHandle
        )
        coVerify(exactly = 1) { getEventByIdUseCase("1") }
    }

    @Test
    fun `successful call to getEventById should make viewModel state also successful`() {
        coEvery { getEventByIdUseCase("1") } returns flow { emit(NetworkResult.Success(getEvent())) }
        eventDetailViewModel = EventDetailViewModel(getEventByIdUseCase,checkInUseCase,shareUseCase, savedStateHandle)
        assert(eventDetailViewModel.eventState.value.event?.id == "1")
    }

    @Test
    fun `if error in getEventById should make viewModel state also contain error`() {
        coEvery { getEventByIdUseCase("10") } returns flow { emit(NetworkResult.Error("error"))}
        every { savedStateHandle.get<String>("eventId") } returns "10"
        eventDetailViewModel = EventDetailViewModel(getEventByIdUseCase,checkInUseCase,shareUseCase, savedStateHandle)
        assert(eventDetailViewModel.eventState.value.error.isNotEmpty())
    }

    /*@Test
    fun `successful call to checkInUseCase should make viewModel state also successful`() = runTest {
        coEvery { getEventByIdUseCase("1") } returns flow { emit(NetworkResult.Success(getEvent())) }
        coEvery { checkInUseCase(getCheckIn()) } returns flow { emit(NetworkResult.Success("200"))}
        eventDetailViewModel = EventDetailViewModel(getEventByIdUseCase,checkInUseCase,shareUseCase, savedStateHandle)
        eventDetailViewModel.doCheckIn("nome", "email@gmail.com")
        delay(2000)
        assert(!eventDetailViewModel.checkInState.value.code.isNullOrEmpty())
    }

    @Test
    fun `unsuccessful call to checkInUseCase should make viewModel state give error`() = runTest{
        coEvery { getEventByIdUseCase("1") } returns flow { emit(NetworkResult.Success(getEvent())) }
        coEvery { checkInUseCase(getCheckIn()) } returns flow { emit(NetworkResult.Error("error"))}
        eventDetailViewModel = EventDetailViewModel(getEventByIdUseCase,checkInUseCase,shareUseCase, savedStateHandle)
        eventDetailViewModel.doCheckIn("nome", "email")
        assert(eventDetailViewModel.checkInState.value.error.isNotEmpty())
    }*/

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

    private fun getCheckIn(): CheckinModel {
        return CheckinModel("1","name", "email")
    }
}