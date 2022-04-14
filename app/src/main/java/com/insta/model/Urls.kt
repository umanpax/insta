package com.insta.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@TypeConverters
@Entity(tableName = "urls")
data class Urls(
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    @SerializedName("id")
    var id: Int = 0,
    @NonNull
    @ColumnInfo(name = "raw")
    @SerializedName("raw")
    var raw: String = "",
    @NonNull
    @ColumnInfo(name = "full")
    @SerializedName("full")
    var full: String = "",
    @NonNull
    @ColumnInfo(name = "regular")
    @SerializedName("regular")
    var regular: String = "",
    @NonNull
    @ColumnInfo(name = "small")
    @SerializedName("small")
    var small: String = "",
    @NonNull
    @ColumnInfo(name = "thumb")
    @SerializedName("thumb")
    var thumb: String = "",
) : Serializable


