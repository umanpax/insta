package com.insta.services.room.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.insta.model.Photo
import com.insta.model.User


@Dao
interface UserDaoAccessRoom {

    /**
     * Get user by username
     * @return
     */
    @Query("SELECT * FROM user WHERE username =:username")
     fun getUserByUsername(username: String): LiveData<User>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     suspend fun insertUser(vararg user: User)


}