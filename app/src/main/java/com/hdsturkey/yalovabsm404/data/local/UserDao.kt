package com.hdsturkey.yalovabsm404.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.hdsturkey.yalovabsm404.data.model.User
import com.hdsturkey.yalovabsm404.data.model.UserName

@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    fun getAll(): LiveData<List<User>>

    @Query("SELECT * FROM user")
    fun getAllOneShot(): List<User>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: User)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: List<User>)

    @Delete
    suspend fun delete(user: User)

    @Query("DELETE FROM user")
    suspend fun deleteAll()

    @Query("SELECT * FROM user WHERE  name LIKE :name LIMIT 1")
    fun findByName(name: UserName): User

    @Query("SELECT * FROM user WHERE  phone LIKE :phone LIMIT 1")
    fun findByPhone(phone: String): User

}