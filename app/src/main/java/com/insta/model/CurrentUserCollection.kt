package com.insta.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class CurrentUserCollection(
    @SerializedName("id")
    var id: String = "",
    @SerializedName("title")
    var title: String = "",
    @SerializedName("published_at")
    var published_at: String = "",
    @SerializedName("last_collected_at")
    var last_collected_at: String = "",
    @SerializedName("updated_at")
    var updated_at: String = "",
    @SerializedName("cover_photo")
    var cover_photo: String = "",
    @SerializedName("user")
    var user :  User  =  User()
) : Serializable


