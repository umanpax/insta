package com.insta.services.interfaces

import retrofit2.http.*
import com.insta.model.Photo
import com.insta.model.Statistics
import com.insta.model.User
import io.reactivex.Observable

/**
 * Created by pierre-alexandrevezinet on 24/03/2022.
 *
 */

interface IService {

    /**
     * Get Photos By Username
     * @return
     */
    @GET("/users/{username}/portfolio")
    fun getPhotosBtUserName(
        @Path("username") username: String,
        @Query("stats") stats: Boolean
    ): Observable<Array<Photo>>


    /**
     * Get Photos
     * @return
     */
    @GET("/photos")
    fun getPhotos(@Query("per_page") perPage: Int): Observable<Array<Photo>>


    /**
     * Get Photos Stats
     * @return
     */
    @GET("/photos/{id}/statistics")
    fun getPhotoStatsById(@Path("id") id: String): Observable<Statistics>

    /**
     * Get User By Username
     * @return
     */
    @GET("/users/{username}")
    fun getUserByUsername(@Path("username") username: String): Observable<User>


}
