package com.paveltinnik.app.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.paveltinnik.app.R
import com.paveltinnik.app.data.db.PersonDatabase
import com.paveltinnik.app.databinding.ActivityMainBinding
import com.paveltinnik.app.repository.PersonRepository

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var viewModel: PersonViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val personRepository = PersonRepository(PersonDatabase(this))
        val viewModelProviderFactory = PersonViewModelProviderFactory(application, personRepository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(PersonViewModel::class.java)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.personsNavHostFragment) as NavHostFragment
        binding.bottomNavigationView.setupWithNavController(navHostFragment.navController)
    }
}