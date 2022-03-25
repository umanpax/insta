package com.insta.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Historical(
    @SerializedName("change")
    var change: Int = 0,
    @SerializedName("resolution")
    var resolution: String = "",
    @SerializedName("quantity")
    var quantity: Int = 0,
    @SerializedName("likes")
    var values: Array<Value> = arrayOf()
) : Serializable


