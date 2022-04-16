import androidx.lifecycle.Observer
import com.insta.utils.MockUtils
import com.insta.ui.splashscreen.SplashScreenViewModel
import com.insta.model.User
import com.insta.utils.ApplicationConstants
import com.insta.utils.KoinApplication
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.mockito.ArgumentCaptor
import org.mockito.Mockito.*


@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class SplashScreenViewModelTest : KoinComponent {

    init {
      KoinApplication()
    }
    private val viewModel: SplashScreenViewModel by inject()
    private var mockUtils: MockUtils = MockUtils()
    private val userObserver: Observer<User> = mockUtils.mock()

    @Before
    fun before() {
        viewModel.repository.liveDataUser.observeForever(userObserver)
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

}