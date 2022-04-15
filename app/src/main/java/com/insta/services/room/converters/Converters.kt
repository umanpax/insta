package com.insta.services.room.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.insta.model.*
import org.json.JSONObject



class Converters {

    @TypeConverter
    fun fromUser(user: User): String {
        return JSONObject().apply {
            put("id", user.id)
            put("username", user.username)
            put("name", user.name)
            put("portfolio_url", user.portfolio_url)
            put("bio", user.bio)
            put("location", user.location)
            put("total_likes", user.total_likes)
            put("total_photos", user.total_photos)
            put("total_collections", user.total_collections)
            put("instagram_username", user.instagram_username)
            put("twitter_username", user.twitter_username)
            put("profile_image", user.profile_image)
            put("links", user.links)
            put("profile_image", user.profile_image)
        }.toString()
    }



    @TypeConverter
    fun fromPhoto(photo: Photo): String {
        return JSONObject().apply {
            put("id", photo.id)
            put("created_at", photo.created_at)
            put("updated_at", photo.updated_at)
            put("width", photo.width)
            put("height", photo.height)
            put("color", photo.color)
            put("blur_hash", photo.blur_hash)
            put("likes", photo.likes)
            put("liked_by_user", photo.liked_by_user)
            put("description", photo.description)
            put("user", photo.user)
            put("current_user_collections", photo.current_user_collections)
            put("urls", photo.urls)
            put("links", photo.links)
        }.toString()
    }


    @TypeConverter
    fun toUser(user: String): User {
        val json = JSONObject(user)
        return User(
            json.getString("id"),
            json.getString("username"),
            json.getString("name"),
            json.getString("portfolio_url"),
            json.getString("bio"),
            json.getString("location"),
            json.getInt("total_likes"),
            json.getInt("total_photos"),
            json.getInt("total_collections"),
            json.getString("instagram_username"),
            json.getString("twitter_username"),
            json.get("profile_image") as ProfileImage,
            json.get("links") as Links
        )
    }



    @TypeConverter
    fun toPhoto(photo: String): Photo {
        val json = JSONObject(photo)

        return Photo(
            json.getString("id"),
            json.getString("created_at"),
            json.getString("updated_at"),
            json.getInt("width"),
            json.getInt("height"),
            json.getString("color"),
            json.getString("blur_hash"),
            json.getInt("likes"),
            json.getBoolean("liked_by_user"),
            json.getString("description"),
            json.get("user") as User,
            json.get("current_user_collections") as Array<CurrentUserCollection>,
            json.get("urls") as Urls,
            json.get("links") as Links,
        )
    }


    @TypeConverter
    fun listOfCurrentUserCollectionToString(list: Array<CurrentUserCollection>): String {
        val gson = Gson()
        return gson.toJson(list)
    }

    @TypeConverter
    fun currentUserCollectionJsonToList(value: String): Array<CurrentUserCollection> {
        val objects = Gson().fromJson(value, Array<CurrentUserCollection>::class.java) as Array<CurrentUserCollection>
        val list = objects
        return list
    }


    @TypeConverter
    fun urlsToJson(value: Urls?): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun urlsJsonToList(value: String): Urls {
        return Gson().fromJson(value, Urls::class.java) as Urls
    }


    @TypeConverter
    fun linksToJson(value: Links?): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun linksJsonToList(value: String): Links {
        return Gson().fromJson(value, Links::class.java) as Links
    }

    @TypeConverter
    fun profileImageToJson(value: ProfileImage?): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun profileImageJsonToList(value: String): ProfileImage {
        return Gson().fromJson(value, ProfileImage::class.java) as ProfileImage
    }

    @TypeConverter
    fun historicalImageToJson(value: Historical?): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun historicalJsonToList(value: String): Historical {
        return Gson().fromJson(value, Historical::class.java) as Historical
    }

    @TypeConverter
    fun likesImageToJson(value: Likes?): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun likesJsonToList(value: String): Likes {
        return Gson().fromJson(value, Likes::class.java) as Likes
    }

    @TypeConverter
    fun listValueToJson(value: Array<Value>?): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun listValueJsonToList(value: String): Array<Value> {
        return Gson().fromJson(value, Array<Value>::class.java) as Array<Value>
    }



}