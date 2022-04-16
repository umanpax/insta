package com.insta.utils

import org.mockito.Mockito

class MockUtils {
    inline fun <reified T> mock(): T = Mockito.mock(T::class.java)
}