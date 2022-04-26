package com.insta

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.insta.model.User
import com.insta.ui.splashscreen.SplashScreenViewModel
import com.insta.utils.ApplicationConstants
import junit.framework.Assert.assertEquals
import org.junit.*
import org.junit.runner.RunWith
import org.mockito.*
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class SplashScreenViewModelTest3 {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var mainViewModel: SplashScreenViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        this.mainViewModel = SplashScreenViewModel()
    }

    @Test
    fun fetchUserRepositories_positiveResponse() {
        // Attacch fake observer
        val observer = mock(Observer::class.java) as Observer<User>
        this.mainViewModel.repository.liveDataUser.observeForever(observer)
        // Invoke
        this.mainViewModel.getUserByUserName("musicpax_", ApplicationConstants.ACCESS_KEY)
        // Verify
        assertEquals(this.mainViewModel.repository.liveDataUser.value?.username, "musicpax_")
    }

}
