package com.u1tramarinet.organizerapp.data.source.room.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import java.time.LocalDateTime

@Entity(tableName = "participants")
data class ParticipantEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "participant_id") val participantId: Int = 0,
    @ColumnInfo(name = "event_id") val eventId: Int,
    @ColumnInfo(name = "user_id") val userId: Int,
    @Embedded(prefix = "attendance_state_") val attendanceState: ConfirmationStateColumn,
    @Embedded(prefix = "payment_state_") val paymentState: ConfirmationStateColumn,
    @ColumnInfo(name = "fee_id") val feeId: Int,
)

data class ConfirmationStateColumn(
    @ColumnInfo(name = "index") val index: Int,
    @ColumnInfo(name = "date") val date: LocalDateTime?,
)

@Entity
data class ParticipantWithOther(
    @Embedded val participant: ParticipantEntity,
    @Relation(
        parentColumn = "user_id",
        entityColumn = "user_id",
        entity = UserEntity::class,
    )
    val user: UserEntity,
    @Relation(
        parentColumn = "fee_id",
        entityColumn = "fee_id",
        entity = FeeEntity::class,
    )
    val fee: FeeEntity,
)