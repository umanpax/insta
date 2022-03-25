package com.insta.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Urls(
    @SerializedName("raw")
    var raw: String = "",
    @SerializedName("full")
    var full: String = "",
    @SerializedName("regular")
    var regular: String = "",
    @SerializedName("small")
    var small: String = "",
    @SerializedName("thumb")
    var thumb: String = "",
) : Serializable


