package com.paveltinnik.app.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.paveltinnik.app.repository.PersonRepository

class PersonViewModelProviderFactory(
    val personRepository: PersonRepository
): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PersonViewModel(personRepository) as T
    }
}