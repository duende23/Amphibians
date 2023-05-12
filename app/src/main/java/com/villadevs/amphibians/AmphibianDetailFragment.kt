package com.villadevs.amphibians

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.villadevs.amphibians.databinding.FragmentAmphibianDetailBinding
import com.villadevs.amphibians.databinding.FragmentAmphibianListBinding
import com.villadevs.amphibians.viewmodel.AmphibianViewModel


private const val ARG_PARAM1 = "param1"

class AmphibianDetailFragment : Fragment() {

    private val sharedViewModel: AmphibianViewModel by activityViewModels()

    private var _binding: FragmentAmphibianDetailBinding? = null
    private val binding get() = _binding!!

    private var param1: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentAmphibianDetailBinding.inflate(inflater, container, false)
        val view = binding.root
        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Attach an observer on the currentSport to update the UI automatically when the data
        // changes.
        sharedViewModel.currentAmphibian.observe(viewLifecycleOwner) {newAmphibian->
            binding.name.text = newAmphibian.name
            //binding?.tv?.load(newSport.sportsImageBanner)
            binding.description.text = newAmphibian.description
        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



}