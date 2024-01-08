package com.u1tramarinet.organizerapp.data.source.room.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity(tableName = "fees")
data class FeeEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "fee_id") val feeId: Int = 0,
    @ColumnInfo(name = "event_id") val eventId: Int,
    @ColumnInfo(name = "title") val title: String?,
    @ColumnInfo(name = "price") val price: Int,
)

data class FeeWithParticipants(
    @Embedded val fee: FeeEntity,
    @Relation(
        parentColumn = "fee_id",
        entityColumn = "fee_id",
        entity = ParticipantEntity::class
    )
    val participants: List<ParticipantEntity>,
)