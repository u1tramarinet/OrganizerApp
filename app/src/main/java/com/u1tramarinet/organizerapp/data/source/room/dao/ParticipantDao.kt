package com.u1tramarinet.organizerapp.data.source.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.u1tramarinet.organizerapp.data.source.room.entity.ParticipantEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ParticipantDao {
    @Query("SELECT * FROM participants")
    suspend fun getAll(): List<ParticipantEntity>

    @Query("SELECT * FROM participants")
    fun getAllStream(): Flow<List<ParticipantEntity>>

    @Query("SELECT * FROM participants WHERE participant_id LIKE :id")
    suspend fun findById(id: Int): ParticipantEntity?

    @Query("SELECT * FROM participants WHERE participant_id LIKE :id")
    fun findByIdStream(id: Int): List<ParticipantEntity>

    @Insert
    suspend fun insert(participantEntity: ParticipantEntity): Long

    @Insert
    suspend fun insertAll(participantEntities: List<ParticipantEntity>): List<Long>

    @Update
    suspend fun update(participantEntity: ParticipantEntity): Int

    @Update
    suspend fun updateAll(participantEntities: List<ParticipantEntity>): Int

    @Delete
    suspend fun delete(participantEntity: ParticipantEntity): Int

    @Query("DELETE FROM participants")
    suspend fun deleteAll()
}