package com.paveltinnik.app.repository

import com.paveltinnik.app.data.db.PersonDatabase
import com.paveltinnik.app.data.network.RetrofitInstance
import com.paveltinnik.app.domain.entity.Person

class PersonRepository(
    val db: PersonDatabase
) {
    suspend fun getPersons(pageNumber: Int) =
        RetrofitInstance.api.getResults(pageNumber = pageNumber)

    suspend fun upsert(person: Person) = db.getPersonDao().upsert(person)

    fun getSavedPersons() = db.getPersonDao().getAllPersons()

    suspend fun deletePerson(person: Person) = db.getPersonDao().deletePerson(person)


}