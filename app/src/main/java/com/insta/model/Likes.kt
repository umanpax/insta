package com.insta.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@TypeConverters
@Entity
data class Likes(
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    @SerializedName("id")
    var id: Int = 0,
    @NonNull
    @ColumnInfo(name = "total")
    @SerializedName("total")
    var total: Int = 0,
    @NonNull
    @ColumnInfo(name = "historical")
    @SerializedName("historical")
    var historical: Historical = Historical()
) : Serializable


