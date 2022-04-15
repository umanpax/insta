package com.insta.services.room.database

import android.content.Context
import androidx.room.*
import com.insta.model.*
import com.insta.services.room.converters.Converters
import com.insta.services.room.dao.PhotoDaoAccessRoom
import com.insta.services.room.dao.UserDaoAccessRoom


@Database(entities = arrayOf(Photo::class,User::class), version = 5, exportSchema = false)
@ProvidedTypeConverter
@TypeConverters(Converters::class)
abstract class RoomDB : RoomDatabase() {
    abstract fun instaDaoPhoto(): PhotoDaoAccessRoom
    abstract fun instaDaoUser(): UserDaoAccessRoom

    companion object{
        fun getDataBase(context: Context): RoomDB {
            return Room.databaseBuilder(context, RoomDB::class.java, "insta")
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}