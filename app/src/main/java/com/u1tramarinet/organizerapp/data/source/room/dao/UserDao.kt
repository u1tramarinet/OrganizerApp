package com.u1tramarinet.organizerapp.data.source.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.u1tramarinet.organizerapp.data.source.room.entity.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Query("SELECT * FROM users")
    suspend fun getAll(): List<UserEntity>

    @Query("SELECT * FROM users")
    fun getAllStream(): Flow<List<UserEntity>>

    @Query("SELECT * FROM users WHERE user_id LIKE :id")
    suspend fun findById(id: Int): UserEntity?

    @Query("SELECT * FROM users WHERE user_id LIKE :id")
    fun findByIdStream(id: Int): Flow<UserEntity>

    @Insert
    suspend fun insert(userEntity: UserEntity): Long

    @Insert
    suspend fun insertAll(userEntities: List<UserEntity>): List<Long>

    @Update
    suspend fun update(userEntity: UserEntity): Int

    @Update
    suspend fun updateAll(userEntities: List<UserEntity>): Int

    @Delete
    suspend fun delete(userEntity: UserEntity): Int

    @Query("DELETE FROM users")
    suspend fun deleteAll()
}