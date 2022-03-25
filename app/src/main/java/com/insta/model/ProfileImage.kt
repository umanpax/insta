package com.insta.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ProfileImage(
    @SerializedName("small")
    var small: String = "",
    @SerializedName("medium")
    var medium: String = "",
    @SerializedName("large")
    var large: String = "",
) : Serializable


