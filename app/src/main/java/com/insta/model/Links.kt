package com.insta.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@TypeConverters
@Entity(tableName = "links")
data class Links(
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    @SerializedName("id")
    var id: Int = 0,
    @NonNull
    @ColumnInfo(name = "self")
    @SerializedName("self")
    var self: String = "",
    @NonNull
    @ColumnInfo(name = "html")
    @SerializedName("html")
    var html: String = "",
    @NonNull
    @ColumnInfo(name = "photos")
    @SerializedName("photos")
    var photos: String = "",
    @NonNull
    @ColumnInfo(name = "likes")
    @SerializedName("likes")
    var likes: String = "",
    @NonNull
    @ColumnInfo(name = "portfolio")
    @SerializedName("portfolio")
    var portfolio: String = "",
    @NonNull
    @ColumnInfo(name = "download")
    @SerializedName("download")
    var download: String = "",
    @NonNull
    @ColumnInfo(name = "download_location")
    @SerializedName("download_location")
    var download_location: String = "",
) : Serializable


