package com.insta.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@TypeConverters
@Entity(tableName = "photo")
data class Photo(
    @NonNull
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    @SerializedName("id")
    var id: String = "",
    @ColumnInfo(name = "created_at")
    @SerializedName("created_at")
    var created_at: String ?= "",
    @ColumnInfo(name = "updated_at")
    @SerializedName("updated_at")
    var updated_at: String ?= "",
    @ColumnInfo(name = "width")
    @SerializedName("width")
    var width: Int ?= 0,
    @ColumnInfo(name = "height")
    @SerializedName("height")
    var height: Int ?= 0,
    @ColumnInfo(name = "color")
    @SerializedName("color")
    var color: String ?= "",
    @ColumnInfo(name = "blur_hash")
    @SerializedName("blur_hash")
    var blur_hash: String ?= "",
    @ColumnInfo(name = "likes")
    @SerializedName("likes")
    var likes: Int ?= 0,
    @ColumnInfo(name = "liked_by_user")
    @SerializedName("liked_by_user")
    var liked_by_user: Boolean ?= false,
    @ColumnInfo(name = "description")
    @SerializedName("description")
    var description: String ?= "",
    @ColumnInfo(name = "user")
    @SerializedName("user")
    var user: User ?= User(),
    @ColumnInfo(name = "current_user_collections")
    @SerializedName("current_user_collections")
    var current_user_collections: Array<CurrentUserCollection>,
    @ColumnInfo(name = "urls")
    @SerializedName("urls")
    var urls: Urls ?= Urls(),
    @ColumnInfo(name = "links")
    @SerializedName("links")
    var links: Links ?= Links(),
) : Serializable



