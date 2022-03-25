package com.insta.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Links(
    @SerializedName("self")
    var self: String = "",
    @SerializedName("html")
    var html: String = "",
    @SerializedName("photos")
    var photos: String = "",
    @SerializedName("likes")
    var likes: String = "",
    @SerializedName("portfolio")
    var portfolio: String = "",
    @SerializedName("download")
    var download: String = "",
    @SerializedName("download_location")
    var download_location: String = "",
) : Serializable


