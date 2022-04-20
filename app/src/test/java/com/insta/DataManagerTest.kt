package com.insta

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.insta.activities.splashscreen.SplashScreenViewModel
import com.insta.model.*
import com.insta.services.repositories.Repository
import com.insta.services.ws.DataManager
import com.insta.utils.AppModule
import com.insta.utils.ApplicationConstants
import com.insta.utils.MockUtils
import io.reactivex.Observable
import org.junit.*
import org.junit.runner.RunWith
import org.koin.core.component.KoinComponent
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DataManagerTest : KoinComponent {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var viewModel: SplashScreenViewModel
    private lateinit var repository: Repository
    private lateinit var dataManager: DataManager
    val mockUtils = MockUtils()
    private lateinit var observer: Observer<User>

    private val expectedUser = User("pQG4TrwwRVE", "musicpax_", "Pier Vez", null, null, null, 0, 0, 0, null, null, ProfileImage(0, "https://images.unsplash.com/placeholder-avatars/extra-large.jpg?ixlib=rb-1.2.1&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=32&w=32", "https://images.unsplash.com/placeholder-avatars/extra-large.jpg?ixlib=rb-1.2.1&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=64&w=64", "https://images.unsplash.com/placeholder-avatars/extra-large.jpg?ixlib=rb-1.2.1&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=128&w=128"), Links(0, "https://api.unsplash.com/users/musicpax_", "https://unsplash.com/@musicpax_", "https://api.unsplash.com/users/musicpax_/photos", "https://api.unsplash.com/users/musicpax_/likes", "https://api.unsplash.com/users/musicpax_/portfolio", "", ""))

    @Before
    fun before() {
        startKoin { modules(AppModule.splashViewModel, AppModule.moduleRepository) }
        observer =  mockUtils.mock()
        viewModel = mockUtils.mock()
        dataManager = mockUtils.mock()
        repository = mockUtils.mock()
        repository.liveDataUser = mockUtils.mock()
    }

    @After
    fun after(){
        stopKoin()
    }

    @Test
    fun fetchUser() {
        lenient().`when`(dataManager.getUserByUserName(ApplicationConstants.USERNAME,ApplicationConstants.ACCESS_KEY)).thenReturn(Observable.just(User()))
    }

    @Test
    fun fetchPhotos() {
        lenient().`when`(dataManager.getPhotos(3,ApplicationConstants.ACCESS_KEY)).thenReturn(Observable.just(
            arrayOf()))
    }

    @Test
    fun fetchPhotoByUsername() {
        lenient().`when`(dataManager.getPhotoByUserName ("surface",false,ApplicationConstants.ACCESS_KEY)).thenReturn(Observable.just(
            arrayOf()))
    }

    @Test
    fun fetchPhotoStatistics() {
        lenient().`when`(dataManager.getPhotoStatisticsById ("1",ApplicationConstants.ACCESS_KEY)).thenReturn(Observable.just(
            Statistics()))
    }


    /*@Test
    fun fetchUser_ShouldReturnUser() {
        repository.getUserByUserName(ApplicationConstants.USERNAME,ApplicationConstants.ACCESS_KEY)
        val captor = ArgumentCaptor.forClass(User::class.java)
        captor.run {
            verify(observer, times(1)).onChanged(capture())
            assertEquals(expectedUser, value)
        }
    }*/
}