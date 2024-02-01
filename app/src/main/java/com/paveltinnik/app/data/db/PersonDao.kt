package com.paveltinnik.app.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.paveltinnik.app.domain.entity.Person

@Dao
interface PersonDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(person: Person): Long

    @Query("SELECT * FROM persons")
    fun getAllPersons(): LiveData<List<Person>>

    @Delete
    suspend fun deletePerson(person: Person)
}