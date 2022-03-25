package com.insta.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Likes(
    @SerializedName("total")
    var total: Int = 0,
    @SerializedName("historical")
    var historical: Historical = Historical()
) : Serializable


