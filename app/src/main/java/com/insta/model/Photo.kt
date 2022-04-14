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
    @NonNull
    @ColumnInfo(name = "created_at")
    @SerializedName("created_at")
    var created_at: String = "",
    @NonNull
    @ColumnInfo(name = "updated_at")
    @SerializedName("updated_at")
    var updated_at: String = "",
    @NonNull
    @ColumnInfo(name = "width")
    @SerializedName("width")
    var width: Int = 0,
    @NonNull
    @ColumnInfo(name = "height")
    @SerializedName("height")
    var height: Int = 0,
    @NonNull
    @ColumnInfo(name = "color")
    @SerializedName("color")
    var color: String = "",
    @NonNull
    @ColumnInfo(name = "blur_hash")
    @SerializedName("blur_hash")
    var blur_hash: String = "",
    @NonNull
    @ColumnInfo(name = "likes")
    @SerializedName("likes")
    var likes: Int = 0,
    @NonNull
    @ColumnInfo(name = "liked_by_user")
    @SerializedName("liked_by_user")
    var liked_by_user: Boolean = false,
    @NonNull
    @ColumnInfo(name = "description")
    @SerializedName("description")
    var description: String = "",
    @NonNull
    @ColumnInfo(name = "user")
    @SerializedName("user")
    var user: User = User(),
    @NonNull
    @ColumnInfo(name = "current_user_collections")
    @SerializedName("current_user_collections")
    var current_user_collections: Array<CurrentUserCollection>,
    @NonNull
    @ColumnInfo(name = "urls")
    @SerializedName("urls")
    var urls: Urls,
    @NonNull
    @ColumnInfo(name = "links")
    @SerializedName("links")
    var links: Links,
) : Serializable {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Photo

        if (id != other.id) return false
        if (created_at != other.created_at) return false
        if (updated_at != other.updated_at) return false
        if (width != other.width) return false
        if (height != other.height) return false
        if (color != other.color) return false
        if (blur_hash != other.blur_hash) return false
        if (likes != other.likes) return false
        if (liked_by_user != other.liked_by_user) return false
        if (description != other.description) return false
        if (user != other.user) return false
        if (!current_user_collections.contentEquals(other.current_user_collections)) return false
        if (urls != other.urls) return false
        if (links != other.links) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + created_at.hashCode()
        result = 31 * result + updated_at.hashCode()
        result = 31 * result + width
        result = 31 * result + height
        result = 31 * result + color.hashCode()
        result = 31 * result + blur_hash.hashCode()
        result = 31 * result + likes
        result = 31 * result + liked_by_user.hashCode()
        result = 31 * result + description.hashCode()
        result = 31 * result + user.hashCode()
        result = 31 * result + current_user_collections.contentHashCode()
        result = 31 * result + urls.hashCode()
        result = 31 * result + links.hashCode()
        return result
    }
}


