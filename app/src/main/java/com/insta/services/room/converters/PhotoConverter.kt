package com.insta.services.room.converters

import androidx.room.TypeConverter
import com.insta.model.*
import org.json.JSONObject

class PhotoConverter {
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

}