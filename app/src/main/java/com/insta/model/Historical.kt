package com.insta.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@TypeConverters
@Entity(tableName = "historical")
data class Historical(
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    @SerializedName("id")
    var id: Int = 0,
    @NonNull
    @ColumnInfo(name = "change")
    @SerializedName("change")
    var change: Int = 0,
    @NonNull
    @ColumnInfo(name = "resolution")
    @SerializedName("resolution")
    var resolution: String = "",
    @NonNull
    @ColumnInfo(name = "quantity")
    @SerializedName("quantity")
    var quantity: Int = 0,
    @NonNull
    @ColumnInfo(name = "likes")
    @SerializedName("likes")
    var values: Array<Value> = arrayOf()
) : Serializable


