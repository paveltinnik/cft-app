package com.paveltinnik.app.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
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

        val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val person = personAdapter.differ.currentList[position]
                viewModel.deletePerson(person)
                Snackbar.make(view, "Succesfully deleted person", Snackbar.LENGTH_LONG).apply {
                    setAction("Undo") {
                        viewModel.savePerson(person)
                    }
                    show()
                }
            }
        }

        ItemTouchHelper(itemTouchHelperCallback).apply {
            attachToRecyclerView(binding.rvSavedPersons)
        }

        viewModel.getSavedPersons().observe(viewLifecycleOwner, Observer {persons ->
            personAdapter.differ.submitList(persons)
        })
    }

    private fun setupRecyclerView() {
        personAdapter = PersonAdapter()
        binding.rvSavedPersons.apply {
            adapter = personAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}