package com.paveltinnik.app.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.paveltinnik.app.domain.entity.Person
import com.paveltinnik.app.domain.entity.PersonsResponse
import com.paveltinnik.app.repository.PersonRepository
import com.paveltinnik.app.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class PersonViewModel(
    val personRepository: PersonRepository
) : ViewModel() {

    val persons: MutableLiveData<Resource<PersonsResponse>> = MutableLiveData()
    var pageNumber = 1

    init {
        getPersons()
    }

    fun getPersons() = viewModelScope.launch {
        persons.postValue(Resource.Loading())
        val response = personRepository.getPersons(pageNumber = pageNumber)
        persons.postValue(handlePersonsResponse(response))
    }

    private fun handlePersonsResponse(response: Response<PersonsResponse>): Resource<PersonsResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    fun savePerson(person: Person) = viewModelScope.launch {
        personRepository.upsert(person)
    }

    fun getSavedPersons() = personRepository.getSavedPersons()

    fun deletePerson(person: Person) = viewModelScope.launch {
        personRepository.deletePerson(person)
    }
}