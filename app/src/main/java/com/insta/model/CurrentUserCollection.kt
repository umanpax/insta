package com.insta.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "currentUserCollection")
@TypeConverters
data class CurrentUserCollection(
    @NonNull
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    @SerializedName("id")
    var id: String = "",
    @NonNull
    @ColumnInfo(name = "title")
    @SerializedName("title")
    var title: String = "",
    @NonNull
    @ColumnInfo(name = "published_at")
    @SerializedName("published_at")
    var published_at: String = "",
    @NonNull
    @ColumnInfo(name = "last_collected_at")
    @SerializedName("last_collected_at")
    var last_collected_at: String = "",
    @NonNull
    @ColumnInfo(name = "updated_at")
    @SerializedName("updated_at")
    var updated_at: String = "",
    @NonNull
    @ColumnInfo(name = "cover_photo")
    @SerializedName("cover_photo")
    var cover_photo: String = "",
    @NonNull
    @ColumnInfo(name = "user")
    @SerializedName("user")
    var user :  User  =  User()
) : Serializable