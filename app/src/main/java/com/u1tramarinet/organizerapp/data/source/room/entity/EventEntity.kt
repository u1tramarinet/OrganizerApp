package com.u1tramarinet.organizerapp.data.source.room.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import java.time.LocalDateTime

@Entity(tableName = "events")
data class EventEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "event_id") val eventId: Int = 0,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "date") val date: LocalDateTime,
    @ColumnInfo(name = "venue_id") val venueId: Int?,
    @ColumnInfo(name = "need_attendance") val needAttendance: Boolean,
    @ColumnInfo(name = "need_fee") val needFee: Boolean,
)

data class EventWithFees(
    @Embedded val event: EventEntity,
    @Relation(
        parentColumn = "event_id",
        entityColumn = "event_id",
        entity = FeeEntity::class,
    )
    val fees: List<FeeEntity>,
)

data class EventWithParticipants(
    @Embedded val event: EventEntity,
    @Relation(
        parentColumn = "event_id",
        entityColumn = "event_id",
        entity = ParticipantEntity::class,
    )
    val participants: List<ParticipantEntity>,
)