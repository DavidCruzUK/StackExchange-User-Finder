package com.lastreact.repository.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {

    /**
     * find Specific user from the user id
     */
    @Query("SELECT * FROM Users")
    suspend fun getAllUsersList(): List<User>

    /**
     * find Specific user from the user id
     */
    @Query("SELECT * FROM Users WHERE userid = :id")
    suspend fun getUserById(id: Int): User

    /**
     * save list of all users
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertListOfUsers(users: List<User>)

    /**
     * Delete all TABLE users.
     */
    @Query("DELETE FROM Users")
    suspend fun deleteAllUsers()

}