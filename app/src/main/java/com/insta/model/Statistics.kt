package com.insta.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Statistics(
    @SerializedName("id")
    var id: String = "",
    @SerializedName("downloads")
    var downloads: Downloads = Downloads(),
    @SerializedName("views")
    var views: Views = Views(),
    @SerializedName("likes")
    var likes: Likes = Likes()
) : Serializable


