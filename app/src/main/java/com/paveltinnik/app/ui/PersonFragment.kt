package com.paveltinnik.app.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.paveltinnik.app.R
import com.paveltinnik.app.databinding.FragmentPersonBinding
import com.paveltinnik.app.domain.entity.Person

class PersonFragment : Fragment() {

    private var _binding: FragmentPersonBinding? = null
    private val binding get() = _binding!!

    lateinit var viewModel: PersonViewModel
    val args: PersonFragmentArgs by navArgs()
    private val person by lazy { args.person }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPersonBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = (activity as MainActivity).viewModel
        setPerson()
        setClickers()

        binding.fab.setOnClickListener {
            viewModel.savePerson(person)
            Snackbar.make(view, "Person saved succesfully", Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun setPerson() {
        Glide.with(binding.ivPersonPhoto).load(person.picture?.large).into(binding.ivPersonPhoto)
        binding.tvPersonName.text = String.format(
            resources.getString(R.string.full_name),
            person.name?.first,
            person.name?.last
        )
        binding.tvPersonAddress.text = String.format(
            resources.getString(R.string.person_address),
            person.location?.street?.streetName,
            person.location?.street?.number
        )
        binding.tvPersonPhone.text = person.phone
    }

    private fun setClickers() {
        binding.tvPersonAddress.setOnClickListener {
            val intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("http://maps.google.com/maps/search/${person.location?.street?.streetName} ${person.location?.street?.number}")
            )
            startActivity(intent)
        }


    }
}