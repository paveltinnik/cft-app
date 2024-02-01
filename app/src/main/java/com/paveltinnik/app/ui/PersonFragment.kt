package com.paveltinnik.app.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.paveltinnik.app.R
import com.paveltinnik.app.databinding.FragmentPersonBinding

class PersonFragment : Fragment() {

    private var _binding: FragmentPersonBinding? = null
    private val binding get() = _binding!!

    lateinit var viewModel: PersonViewModel
    val args: PersonFragmentArgs by navArgs()

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
    }

    private fun setPerson() {
        val person = args.person

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
}