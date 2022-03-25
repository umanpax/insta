package com.insta.utils

import okhttp3.Interceptor
import java.io.IOException

/**
 * Created by pierre-alexandrevezinet on 24/03/2022.
 *
 */

class AuthenticationInterceptor : Interceptor {

    private var accessToken: String? = null
    private var contentType: String? = null
    private var prefsManager: PrefsManager? = null

    constructor(token: String) {
        this.accessToken = token
        this.contentType = ApplicationConstants.CONTENT_TYPE_APPLICATION_JSON
    }

    constructor(token: String, contentType: String) {
        this.accessToken = token
        this.contentType = contentType
    }

    constructor () {
        this.accessToken = ""
        this.contentType = ApplicationConstants.CONTENT_TYPE_APPLICATION_JSON
    }

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
        val original = chain.request()
        val builder = original.newBuilder()

        builder.header("content-type", contentType!!)
            .header("accept-language", "fr")
            .header("cache-control", "no-cache")
            .header("Authorization", "Client-ID $accessToken")
            .header("dev", "PAx")

        val request = builder.build()
        val response = chain.proceed(request)

        return response
    }


}