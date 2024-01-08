package com.u1tramarinet.organizerapp.data.source.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.u1tramarinet.organizerapp.data.source.room.dao.EventDao
import com.u1tramarinet.organizerapp.data.source.room.dao.FeeDao
import com.u1tramarinet.organizerapp.data.source.room.dao.ParticipantDao
import com.u1tramarinet.organizerapp.data.source.room.dao.UserDao
import com.u1tramarinet.organizerapp.data.source.room.dao.VenueDao
import com.u1tramarinet.organizerapp.data.source.room.entity.EventEntity
import com.u1tramarinet.organizerapp.data.source.room.entity.FeeEntity
import com.u1tramarinet.organizerapp.data.source.room.entity.ParticipantEntity
import com.u1tramarinet.organizerapp.data.source.room.entity.UserEntity
import com.u1tramarinet.organizerapp.data.source.room.entity.VenueEntity

@Database(
    entities = [
        EventEntity::class,
        ParticipantEntity::class,
        UserEntity::class,
        FeeEntity::class,
        VenueEntity::class,
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(RoomConverters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun eventDao(): EventDao
    abstract fun participantDao(): ParticipantDao
    abstract fun userDao(): UserDao
    abstract fun feeDao(): FeeDao
    abstract fun venueDao(): VenueDao
}