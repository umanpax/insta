package com.insta

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.insta.activities.splashscreen.SplashScreenViewModel
import com.insta.model.*
import com.insta.services.ServiceGenerator
import com.insta.services.interfaces.IService
import com.insta.services.repositories.Repository
import com.insta.services.ws.DataManager
import com.insta.utils.AppModule
import com.insta.utils.ApplicationConstants
import com.insta.utils.MockUtils
import io.reactivex.Observable
import io.reactivex.observers.TestObserver
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
    private lateinit var serviceGenerator: ServiceGenerator
    private lateinit var service: IService

    private val expectedUser = User(
        "pQG4TrwwRVE",
        "musicpax_",
        "Pier Vez",
        null,
        null,
        null,
        0,
        0,
        0,
        null,
        null,
        ProfileImage(
            0,
            "https://images.unsplash.com/placeholder-avatars/extra-large.jpg?ixlib=rb-1.2.1&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=32&w=32",
            "https://images.unsplash.com/placeholder-avatars/extra-large.jpg?ixlib=rb-1.2.1&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=64&w=64",
            "https://images.unsplash.com/placeholder-avatars/extra-large.jpg?ixlib=rb-1.2.1&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=128&w=128"
        ),
        Links(
            0,
            "https://api.unsplash.com/users/musicpax_",
            "https://unsplash.com/@musicpax_",
            "https://api.unsplash.com/users/musicpax_/photos",
            "https://api.unsplash.com/users/musicpax_/likes",
            "https://api.unsplash.com/users/musicpax_/portfolio",
            "",
            ""
        )
    )

    @Before
    fun before() {
        startKoin { modules(AppModule.splashViewModel, AppModule.moduleRepository) }
        observer = mockUtils.mock()
        viewModel = mockUtils.mock()
        serviceGenerator = mockUtils.mock()
        service = mockUtils.mock()
        dataManager = DataManager(serviceGenerator)
        repository = mockUtils.mock()
        repository.liveDataUser = mockUtils.mock()
    }

    @After
    fun after() {
        stopKoin()
    }

    @Test
    fun fetchUser() {
        `when`(serviceGenerator.createService(IService::class.java,ApplicationConstants.ACCESS_KEY)).thenReturn(service)
        val mOservable = Observable.just(User())
        `when`(service.getUserByUsername("")).thenReturn(mOservable)
        val observable = dataManager.getUserByUserName("", ApplicationConstants.ACCESS_KEY)
        val testObserver = TestObserver<User>()
        observable.subscribe(testObserver)
        testObserver.assertComplete()
        testObserver.assertNoErrors()
        testObserver.assertValueCount(1)
    }

    @Test
    fun fetchPhotos() {
        `when`(serviceGenerator.createService(IService::class.java,ApplicationConstants.ACCESS_KEY)).thenReturn(service)
        val arrayPhoto :  Array<Photo> = arrayOf()
        val mOservable = Observable.just(arrayPhoto)
        `when`(service.getPhotos(1)).thenReturn(mOservable)
        val observable = dataManager.getPhotos(1, ApplicationConstants.ACCESS_KEY)
        val testObserver = TestObserver<Array<Photo>>()
        observable.subscribe(testObserver)
        testObserver.assertComplete()
        testObserver.assertNoErrors()
        testObserver.assertValueCount(1)
    }

    @Test
    fun fetchPhotoByUsername() {
        `when`(serviceGenerator.createService(IService::class.java,ApplicationConstants.ACCESS_KEY)).thenReturn(service)
        val arrayPhoto :  Array<Photo> = arrayOf()
        val mOservable = Observable.just(arrayPhoto)
        `when`(service.getPhotosByUserName("surface", false)).thenReturn(mOservable)
        val observable = dataManager.getPhotoByUserName("surface",false, ApplicationConstants.ACCESS_KEY)
        val testObserver = TestObserver<Array<Photo>>()
        observable.subscribe(testObserver)
        testObserver.assertComplete()
        testObserver.assertNoErrors()
        testObserver.assertValueCount(1)
    }

    @Test
    fun fetchPhotoStatistics() {
        `when`(serviceGenerator.createService(IService::class.java,ApplicationConstants.ACCESS_KEY)).thenReturn(service)
        val mOservable = Observable.just(Statistics())
        `when`(service.getPhotoStatsById("0")).thenReturn(mOservable)
        val observable = dataManager.getPhotoStatisticsById("0", ApplicationConstants.ACCESS_KEY)
        val testObserver = TestObserver<Statistics>()
        observable.subscribe(testObserver)
        testObserver.assertComplete()
        testObserver.assertNoErrors()
        testObserver.assertValueCount(1)
    }
}