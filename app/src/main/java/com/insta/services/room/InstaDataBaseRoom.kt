package com.insta.services.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.insta.model.*
import com.insta.services.room.converters.Converters


@Database(entities = [Photo::class,User::class,CurrentUserCollection::class, Likes::class, Links::class,ProfileImage::class,Urls::class,Historical::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class InstaDatabase : RoomDatabase() {
    abstract fun instaDaoPhoto(): PhotoDaoAccessRoom
    abstract fun instaDaoUser(): UserDaoAccessRoom

    companion object{
        fun getDataBase(context: Context): InstaDatabase {
            return Room.databaseBuilder(context, InstaDatabase::class.java, "insta")
                .addTypeConverter(Converters::class)
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}