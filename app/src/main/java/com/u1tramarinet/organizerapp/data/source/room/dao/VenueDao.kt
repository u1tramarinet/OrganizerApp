package com.u1tramarinet.organizerapp.data.source.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.u1tramarinet.organizerapp.data.source.room.entity.VenueEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface VenueDao {
    @Query("SELECT * FROM venues")
    suspend fun getAll(): List<VenueEntity>

    @Query("SELECT * FROM venues")
    fun getAllStream(): Flow<List<VenueEntity>>

    @Query("SELECT * FROM venues WHERE venue_id LIKE :id")
    suspend fun findById(id: Int): VenueEntity?

    @Query("SELECT * FROM venues WHERE venue_id LIKE :id")
    fun findByIdStream(id: Int): Flow<VenueEntity>

    @Insert
    suspend fun insert(venueEntity: VenueEntity): Long

    @Insert
    suspend fun insertAll(venueEntities: List<VenueEntity>): List<Long>

    @Update
    suspend fun update(venueEntity: VenueEntity): Int

    @Update
    suspend fun updateAll(venueEntities: List<VenueEntity>): Int

    @Delete
    suspend fun delete(venueEntity: VenueEntity): Int

    @Query("DELETE FROM venues")
    suspend fun deleteAll()
}