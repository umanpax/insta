package com.insta

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.bumptech.glide.load.engine.Resource
import com.insta.model.User
import com.insta.services.repositories.Repository
import com.insta.ui.splashscreen.SplashScreenViewModel
import com.insta.utils.AppModule
import com.insta.utils.ApplicationConstants
import com.insta.utils.MockUtils
import io.reactivex.Single
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.*
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner


@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class SplashScreenViewModelTest2 : KoinComponent {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    init {
      startKoin {
          modules(AppModule.splashViewModel,AppModule.moduleRepository)
      }
    }
    private val testDispatcher = StandardTestDispatcher()
    //private val viewModel: SplashScreenViewModel = SplashScreenViewModel()
    private var mockUtils: MockUtils = MockUtils()
    private val userObserver: Observer<User> = mockUtils.mock()
    private val viewModel: SplashScreenViewModel by inject()

    @Mock
    private lateinit var homeRepository: Repository
    @Mock
    private var observer:Observer<User> = mockUtils.mock()
    @Captor
    private lateinit var argumentCaptor:  ArgumentCaptor<User>
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setupMyTripViewModel() {
        RxJavaPlugins.setIoSchedulerHandler{ Schedulers.trampoline()}
    }

    @Test
    fun fetchUser(){
        viewModel.repository.liveDataUser.observeForever(observer)
        viewModel.getUserByUserName("musicpax_",ApplicationConstants.ACCESS_KEY)
        val value = argumentCaptor.value
        Assert.assertEquals("musicpax_",value.username)
    }


}