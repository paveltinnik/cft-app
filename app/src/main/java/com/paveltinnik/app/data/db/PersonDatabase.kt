package com.paveltinnik.app.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.paveltinnik.app.domain.entity.Person

@Database(
    entities = [Person::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class PersonDatabase : RoomDatabase() {

    abstract fun getPersonDao(): PersonDao

    companion object {
        @Volatile
        private var instance: PersonDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also { instance = it }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                PersonDatabase::class.java,
                "person_db.db"
            ).build()
    }
}