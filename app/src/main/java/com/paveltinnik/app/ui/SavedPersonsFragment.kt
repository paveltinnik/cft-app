package com.paveltinnik.app.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.paveltinnik.app.R
import com.paveltinnik.app.adapter.PersonAdapter
import com.paveltinnik.app.databinding.FragmentMainBinding
import com.paveltinnik.app.databinding.FragmentSavedPersonsBinding

class SavedPersonsFragment : Fragment() {

    private var _binding: FragmentSavedPersonsBinding? = null
    private val binding get() = _binding!!

    private lateinit var personAdapter: PersonAdapter

    lateinit var viewModel: PersonViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSavedPersonsBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = (activity as MainActivity).viewModel

        setupRecyclerView()

        personAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("person", it)
            }
            findNavController().navigate(
                R.id.action_savedPersonsFragment_to_personFragment,
                bundle
            )
        }
    }

    private fun setupRecyclerView() {
        personAdapter = PersonAdapter()
        binding.rvSavedPersons.apply {
            adapter = personAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}