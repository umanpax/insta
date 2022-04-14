package com.insta.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Value(
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    @SerializedName("id")
    var id: Int = 0,
    @NonNull
    @ColumnInfo(name = "date")
    @SerializedName("date")
    var date: String = "",
    @NonNull
    @ColumnInfo(name = "value")
    @SerializedName("value")
    var value: Int = 0
) : Serializable


