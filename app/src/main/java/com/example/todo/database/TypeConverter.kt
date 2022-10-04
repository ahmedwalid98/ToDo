package com.example.todo.database

import androidx.room.TypeConverter
import java.util.*

class TypeConverter {

    @TypeConverter
    fun fromDate(date: Date):Long{
        return date.time
    }

    @TypeConverter
    fun fromTime(time: Long):Date{
        return Date(time)
    }
}