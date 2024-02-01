package com.paveltinnik.app.data.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.paveltinnik.app.domain.entity.Name

class Converters {

    @TypeConverter
    fun fromName(name: Name): String? {
        val gson = GsonBuilder()
            .setPrettyPrinting()
            .create()

        return gson.toJson(name)
    }

    @TypeConverter
    fun toName(string: String): Name? {
        return Gson().fromJson(string, Name::class.java)
    }
}