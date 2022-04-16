import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.insta.model.User
import com.insta.ui.splashscreen.SplashScreenViewModel
import com.insta.utils.AppModule
import com.insta.utils.ApplicationConstants
import com.insta.utils.MockUtils
import io.reactivex.Single
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin
import org.mockito.ArgumentCaptor
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner


@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class SplashScreenViewModelTest : KoinComponent {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    init {
      startKoin {
          modules(AppModule.splashViewModel,AppModule.moduleRepository)
      }
    }
    private val testDispatcher = StandardTestDispatcher()
    private val viewModel: SplashScreenViewModel by inject()
    private var mockUtils: MockUtils = MockUtils()
    private val userObserver: Observer<User> = mockUtils.mock()


    @Before
    fun before() {
        Dispatchers.setMain(testDispatcher)
        viewModel.repository.liveDataUser.observeForever(userObserver)
    }

    @After
    fun after() {
        Dispatchers.resetMain()
    }

    @Test
    fun fetchUser() {
        viewModel.repository.getUserByUserName("musicpax_",ApplicationConstants.ACCESS_KEY)
            val captor = ArgumentCaptor.forClass(User::class.java)
            captor.run {
                verify(userObserver, times(1)).onChanged(capture())
                assertEquals("musicpax_", value.username)
            }
    }

  /*  @Test
    fun testGetCurrentUser() {
        runBlocking {
            val user = mock(User::class.java)
            `when`(viewModel.repository.getUserByUserName("musicpax_",ApplicationConstants.ACCESS_KEY))
                .thenReturn(user)
            userViewModel.states.captureValues {
                userViewModel.getCurrentUser()
                assertSendsValues(100, LoadingState, UserConnected(user))
            }
        }
    }*/
}