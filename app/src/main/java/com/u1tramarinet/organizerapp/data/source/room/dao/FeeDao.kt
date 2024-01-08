package com.u1tramarinet.organizerapp.data.source.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.u1tramarinet.organizerapp.data.source.room.entity.FeeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FeeDao {
    @Query("SELECT * FROM fees")
    suspend fun getAll(): List<FeeEntity>

    @Query("SELECT * FROM fees")
    fun getAllStream(): Flow<List<FeeEntity>>

    @Query("SELECT * FROM fees WHERE fee_id LIKE :id")
    suspend fun findById(id: Int): FeeEntity?

    @Query("SELECT * FROM fees WHERE fee_id LIKE :id")
    fun findByIdStream(id: Int): Flow<FeeEntity>

    @Insert
    suspend fun insert(feeEntity: FeeEntity): Long

    @Insert
    suspend fun insertAll(feeEntities: List<FeeEntity>): List<Long>

    @Update
    suspend fun update(feeEntity: FeeEntity): Int

    @Update
    suspend fun updateAll(feeEntities: List<FeeEntity>): Int

    @Delete
    suspend fun delete(feeEntity: FeeEntity): Int

    @Query("DELETE FROM fees")
    suspend fun deleteAll()
}