package com.insta.services.room.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.insta.model.Photo


@Dao
interface PhotoDaoAccessRoom {
    /**
     * Get Photos
     * @return
     */
    @Query("SELECT * FROM photo LIMIT :perPage")
     fun getPhotos(perPage: Int): LiveData<List<Photo>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPhoto(vararg photo: Photo)

}