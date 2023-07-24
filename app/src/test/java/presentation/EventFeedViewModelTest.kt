package presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.hermes.nextevent.domain.use_case.get_events.GetEventsUseCase
import com.hermes.nextevent.presentation.eventfeed.EventFeedState
import com.hermes.nextevent.presentation.eventfeed.EventFeedViewModel
import com.hermes.nextevent.util.NetworkResult
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import util.CoroutineTestRule

@OptIn(ExperimentalCoroutinesApi::class)
class EventFeedViewModelTest {
    @get:Rule
    val instantTask = InstantTaskExecutorRule()
    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    private val getEventsUseCase: GetEventsUseCase = mockk(relaxed = true)

    private lateinit var eventFeedViewModel: EventFeedViewModel


    @Test
    fun `should call getEventsUseCase on viewModel init`() = runTest {
        eventFeedViewModel = EventFeedViewModel(getEventsUseCase)
        coVerify(exactly = 1) { getEventsUseCase() }
    }

    @Test
    fun `successful call to getEventsUseCase should make viewModel state successful`() = runTest {
        coEvery { getEventsUseCase() } returns flow { emit(NetworkResult.Success(emptyList())) }
        eventFeedViewModel = EventFeedViewModel(getEventsUseCase)
        assert(eventFeedViewModel.state.value.error.isEmpty())
    }

    @Test
    fun `if error in getEventsUseCase, viewModel state should be error also`(){
        coEvery { getEventsUseCase() } returns flow { emit(NetworkResult.Error("error")) }
        eventFeedViewModel = EventFeedViewModel(getEventsUseCase)
        assert(eventFeedViewModel.state.value.error == "error")
    }

}