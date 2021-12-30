package kpfu.itis.valisheva.app1.db

import androidx.room.TypeConverter
import java.util.*

class Converter {

    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let {
            Date(it)
        }
    }

    @TypeConverter
    fun toTimestamp(date: Date?): Long? {
        return date?.time
    }
}
