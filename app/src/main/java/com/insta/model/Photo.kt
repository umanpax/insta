package com.insta.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Photo(
    @SerializedName("id")
    var id: String = "",
    @SerializedName("created_at")
    var created_at: String = "",
    @SerializedName("updated_at")
    var updated_at: String = "",
    @SerializedName("width")
    var width: Int = 0,
    @SerializedName("height")
    var height: Int = 0,
    @SerializedName("color")
    var color: String = "",
    @SerializedName("blur_hash")
    var blur_hash: String = "",
    @SerializedName("likes")
    var likes: Int = 0,
    @SerializedName("liked_by_user")
    var liked_by_user: Boolean = false,
    @SerializedName("description")
    var description: String = "",
    @SerializedName("user")
    var user: User = User(),
    @SerializedName("current_user_collections")
    var current_user_collections: Array<CurrentUserCollection>,
    @SerializedName("urls")
    var urls: Urls,
    @SerializedName("links")
    var links: Links,
) : Serializable


