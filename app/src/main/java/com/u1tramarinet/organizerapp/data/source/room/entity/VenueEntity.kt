package com.u1tramarinet.organizerapp.data.source.room.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity(tableName = "venues")
data class VenueEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "venue_id") val venueId: Int = 0,
    val name: String,
    val postCode: String? = null,
    val address: String? = null,
    val url: String? = null,
)

data class VenueWithEvents(
    @Embedded val venue: VenueEntity,
    @Relation(
        parentColumn = "venue_id",
        entityColumn = "venue_id",
        entity = EventEntity::class,
    )
    val events: List<EventEntity>
)