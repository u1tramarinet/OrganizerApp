package com.u1tramarinet.organizerapp.data.source.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.u1tramarinet.organizerapp.data.source.room.entity.EventEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface EventDao {
    @Query("SELECT * FROM events")
    suspend fun getAll(): List<EventEntity>

    @Query("SELECT * FROM events")
    fun getAllStream(): Flow<List<EventEntity>>

    @Query("SELECT * FROM events WHERE event_id LIKE :id")
    suspend fun findById(id: Int): EventEntity?

    @Query("SELECT * FROM events WHERE event_id LIKE :id")
    fun findByIdStream(id: Int): Flow<EventEntity>

    @Insert
    suspend fun insert(eventEntity: EventEntity): Long

    @Insert
    suspend fun insertAll(eventEntities: List<EventEntity>): List<Long>

    @Update
    suspend fun update(eventEntity: EventEntity): Int

    @Update
    suspend fun updateAll(eventEntities: List<EventEntity>): Int

    @Delete
    suspend fun delete(eventEntity: EventEntity): Int

    @Query("DELETE FROM events")
    suspend fun deleteAll()
}