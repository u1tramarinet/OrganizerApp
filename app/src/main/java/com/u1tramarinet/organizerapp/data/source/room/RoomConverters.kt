package com.u1tramarinet.organizerapp.data.source.room

import androidx.room.TypeConverter
import java.time.LocalDateTime

class RoomConverters {
    @TypeConverter
    fun fromLocalDateTime(localDateTime: LocalDateTime): String {
        return localDateTime.toString()
    }

    @TypeConverter
    fun toLocalDateTime(stringLocalDateTime: String): LocalDateTime {
        return LocalDateTime.parse(stringLocalDateTime)
    }
}