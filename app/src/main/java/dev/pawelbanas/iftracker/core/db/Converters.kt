package dev.pawelbanas.iftracker.core.db

import androidx.room.TypeConverter
import java.time.Duration
import java.time.LocalDateTime

class Converters {
    @TypeConverter
    fun localDateTimeToString(dateTime: LocalDateTime?) = dateTime?.toString()

    @TypeConverter
    fun stringToLocalDateTime(value: String?) = value?.let { LocalDateTime.parse(it) }

    @TypeConverter
    fun durationToString(duration: Duration?) = duration?.toString()

    @TypeConverter
    fun stringToDuration(value: String?) = value?.let { Duration.parse(it) }
}