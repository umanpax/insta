package com.insta.services.room.converters

import androidx.room.TypeConverter
import com.insta.model.Links
import com.insta.model.ProfileImage
import com.insta.model.User
import org.json.JSONObject

class UserConverter {
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

}