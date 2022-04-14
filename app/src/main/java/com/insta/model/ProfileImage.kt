package com.insta.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@TypeConverters
@Entity(tableName = "profileImage")
data class ProfileImage(
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    @SerializedName("id")
    var id: Int = 0,
    @NonNull
    @ColumnInfo(name = "small")
    @SerializedName("small")
    var small: String = "",
    @NonNull
    @ColumnInfo(name = "medium")
    @SerializedName("medium")
    var medium: String = "",
    @NonNull
    @ColumnInfo(name = "large")
    @SerializedName("large")
    var large: String = "",
) : Serializable


