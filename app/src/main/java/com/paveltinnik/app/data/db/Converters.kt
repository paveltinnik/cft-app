package com.paveltinnik.app.data.db

import androidx.room.TypeConverter
import com.paveltinnik.app.domain.entity.Name

class Converters {

    @TypeConverter
    fun fromName(name: Name): String? {
        return name.first
    }

    @TypeConverter
    fun toName(string: String): Name? {
        return Name(string, string, string)
    }
}