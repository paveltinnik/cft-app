package com.paveltinnik.app.ui

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.ConnectivityManager.TYPE_ETHERNET
import android.net.ConnectivityManager.TYPE_MOBILE
import android.net.ConnectivityManager.TYPE_WIFI
import android.net.NetworkCapabilities.TRANSPORT_CELLULAR
import android.net.NetworkCapabilities.TRANSPORT_ETHERNET
import android.net.NetworkCapabilities.TRANSPORT_WIFI
import android.os.Build
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.paveltinnik.app.PersonsApplication
import com.paveltinnik.app.domain.entity.Person
import com.paveltinnik.app.domain.entity.PersonsResponse
import com.paveltinnik.app.repository.PersonRepository
import com.paveltinnik.app.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException

class PersonViewModel(
    app: Application,
    val personRepository: PersonRepository
) : AndroidViewModel(app) {

    val persons: MutableLiveData<Resource<PersonsResponse>> = MutableLiveData()
    var pageNumber = 1
    var personResponse: PersonsResponse? = null

    init {
        getPersons()
    }

    fun getPersons() = viewModelScope.launch {
        safePersonsCall()
    }

    private fun handlePersonsResponse(response: Response<PersonsResponse>): Resource<PersonsResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                pageNumber++
                if (personResponse == null) {
                    personResponse = resultResponse
                } else {
                    val oldPersons = personResponse?.results
                    val newPersons = resultResponse.results
                    oldPersons?.addAll(newPersons)
                }
                return Resource.Success(personResponse ?: resultResponse)
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

    private suspend fun safePersonsCall() {
        persons.postValue(Resource.Loading())
        try {
            if (hasInternetConnection()) {
                val response = personRepository.getPersons(pageNumber = pageNumber)
                persons.postValue(handlePersonsResponse(response))
            } else {
                persons.postValue(Resource.Error("No internet connection"))
            }
        } catch (t: Throwable) {
            when (t) {
                is IOException -> persons.postValue(Resource.Error("Network failure"))
                else -> persons.postValue(Resource.Error("Conversion Error"))
            }
        }
    }

    private fun hasInternetConnection(): Boolean {
        val connectivityManager = getApplication<PersonsApplication>().getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val activeNetwork = connectivityManager.activeNetwork ?: return false
            val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
            return when{
                capabilities.hasTransport(TRANSPORT_WIFI) -> true
                capabilities.hasTransport(TRANSPORT_CELLULAR) -> true
                capabilities.hasTransport(TRANSPORT_ETHERNET) -> true
                else -> false
            }
        } else {
            connectivityManager.activeNetworkInfo?.run {
                return when(type) {
                    TYPE_WIFI -> true
                    TYPE_MOBILE -> true
                    TYPE_ETHERNET -> true
                    else -> false
                }
            }
        }
        return false
    }
}