package com.paveltinnik.app.ui

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.paveltinnik.app.repository.PersonRepository

class PersonViewModelProviderFactory(
    val app: Application,
    val personRepository: PersonRepository
): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PersonViewModel(app, personRepository) as T
    }
}