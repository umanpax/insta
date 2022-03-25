package com.insta.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Downloads(
    @SerializedName("total")
    var total: Int = 0,
    @SerializedName("historical")
    var historical: Historical = Historical()
) : Serializable


