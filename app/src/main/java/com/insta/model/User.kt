package com.insta.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@TypeConverters
@Entity(tableName = "user")
data class User(
    @NonNull
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    @SerializedName("id")
    var id: String = "",
    @NonNull
    @ColumnInfo(name = "username")
    @SerializedName("username")
    var username: String = "",
    @NonNull
    @ColumnInfo(name = "name")
    @SerializedName("name")
    var name: String = "",
    @NonNull
    @ColumnInfo(name = "portfolio_url")
    @SerializedName("portfolio_url")
    var portfolio_url: String = "",
    @NonNull
    @ColumnInfo(name = "bio")
    @SerializedName("bio")
    var bio: String = "",
    @NonNull
    @ColumnInfo(name = "location")
    @SerializedName("location")
    var location: String = "",
    @NonNull
    @ColumnInfo(name = "total_likes")
    @SerializedName("total_likes")
    var total_likes: Int = 0,
    @NonNull
    @ColumnInfo(name = "total_photos")
    @SerializedName("total_photos")
    var total_photos: Int = 0,
    @NonNull
    @ColumnInfo(name = "total_collections")
    @SerializedName("total_collections")
    var total_collections: Int = 0,
    @NonNull
    @ColumnInfo(name = "instagram_username")
    @SerializedName("instagram_username")
    var instagram_username: String = "",
    @NonNull
    @ColumnInfo(name = "twitter_username")
    @SerializedName("twitter_username")
    var twitter_username: String = "",
    @NonNull
    @ColumnInfo(name = "profile_image")
    @SerializedName("profile_image")
    var profile_image: ProfileImage = ProfileImage(),
    @NonNull
    @ColumnInfo(name = "links")
    @SerializedName("links")
    var links: Links = Links(),
) : Serializable


