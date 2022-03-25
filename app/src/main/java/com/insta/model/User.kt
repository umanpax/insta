package com.insta.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class User(
    @SerializedName("id")
    var id: String = "",
    @SerializedName("username")
    var username: String = "",
    @SerializedName("name")
    var name: String = "",
    @SerializedName("portfolio_url")
    var portfolio_url: String = "",
    @SerializedName("bio")
    var bio: String = "",
    @SerializedName("location")
    var location: String = "",
    @SerializedName("total_likes")
    var total_likes: Int = 0,
    @SerializedName("total_photos")
    var total_photos: Int = 0,
    @SerializedName("total_collections")
    var total_collections: Int = 0,
    @SerializedName("instagram_username")
    var instagram_username: String = "",
    @SerializedName("twitter_username")
    var twitter_username: String = "",
    @SerializedName("profile_image")
    var profile_image: ProfileImage = ProfileImage(),
    @SerializedName("links")
    var links: Links = Links(),
) : Serializable


