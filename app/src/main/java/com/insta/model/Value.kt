package com.insta.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Value(
    @SerializedName("date")
    var date: String = "",
    @SerializedName("value")
    var value: Int = 0
) : Serializable


