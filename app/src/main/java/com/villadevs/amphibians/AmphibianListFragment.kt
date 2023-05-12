package com.villadevs.amphibians

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.villadevs.amphibians.adapter.AmphibianAdapter
import com.villadevs.amphibians.databinding.FragmentAmphibianListBinding
import com.villadevs.amphibians.viewmodel.AmphibianViewModel

private const val ARG_PARAM1 = "param1"

class AmphibianListFragment : Fragment() {

    private val sharedViewModel: AmphibianViewModel by activityViewModels()

    private var _binding: FragmentAmphibianListBinding? = null
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
        _binding = FragmentAmphibianListBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedViewModel.status.observe(viewLifecycleOwner) { newStatus ->
            //binding.tvMars.text = newStatus
            when (newStatus) {
                AmphibianViewModel.AmphibianApiStatus.LOADING -> {
                    binding.ivStatusImage.visibility = View.VISIBLE
                    binding.ivStatusImage.setImageResource(R.drawable.loading_animation)
                }
                AmphibianViewModel.AmphibianApiStatus.ERROR -> {
                    binding.ivStatusImage.visibility = View.VISIBLE
                    binding.ivStatusImage.setImageResource(R.drawable.ic_connection_error)
                }
                AmphibianViewModel.AmphibianApiStatus.DONE -> {
                    binding.ivStatusImage.visibility = View.GONE
                }
            }
        }



        // Initialize the adapter and set it to the RecyclerView.
        val adapter = AmphibianAdapter {
            sharedViewModel.updateCurrentAmphibian(it)
            // Navigate to the details screen
            val action = AmphibianListFragmentDirections.actionAmphibianListFragmentToAmphibianDetailFragment()
            this.findNavController().navigate(action)
        }




        binding.rvRecyclerView.adapter = adapter

        sharedViewModel.amphibian.observe(viewLifecycleOwner) { newAmphibian ->
            adapter.submitList(newAmphibian)
        }


    }




    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}