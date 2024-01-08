package com.u1tramarinet.organizerapp.data.source.room.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "user_id") val userId: Int = 0,
    @ColumnInfo("name") val name: String,
    @ColumnInfo("phone_number") val phoneNumber: String?,
    @ColumnInfo("mail_address") val mainAddress: String?
)

data class UserWithParticipants(
    @Embedded val user: UserEntity,
    @Relation(
        parentColumn = "user_id",
        entityColumn = "user_id",
        entity = ParticipantEntity::class,
    )
    val participants: List<ParticipantEntity>,
)
