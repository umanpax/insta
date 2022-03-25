package com.insta.services.ws

import com.insta.model.Photo
import com.insta.model.Statistics
import com.insta.model.User
import com.insta.services.ServiceGenerator
import com.insta.services.interfaces.IService
import io.reactivex.Observable


/**
 * Created by pierre-alexandrevezinet on 24/03/2022.
 *
 */
class DataManager {

    var baseUrl: String = ""
    var serviceGenerator: ServiceGenerator

    /**
     * @param baseUrl
     */
    constructor(baseUrl: String) {
        this.baseUrl = baseUrl
        serviceGenerator = ServiceGenerator(baseUrl)
    }

    fun getPhotoByUserName(username : String, stats : Boolean, token : String): Observable<Array<Photo>> {
        val service = serviceGenerator.createService(IService::class.java,token)
        return service.getPhotosByUserName(username, stats)
    }

    fun getPhotos(perPage : Int, token : String): Observable<Array<Photo>> {
        val service = serviceGenerator.createService(IService::class.java,token)
        return service.getPhotos(perPage)
    }

    fun getPhotoStatisticsById(id : String, token : String): Observable<Statistics> {
        val service = serviceGenerator.createService(IService::class.java,token)
        return service.getPhotoStatsById(id)
    }

    fun getUserByUserName(username : String, token : String): Observable<User> {
        val service = serviceGenerator.createService(IService::class.java,token)
        return service.getUserByUsername(username)
    }
}



