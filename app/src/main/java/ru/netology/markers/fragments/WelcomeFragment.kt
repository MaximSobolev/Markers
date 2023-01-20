package ru.netology.markers.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import ru.netology.markers.R
import ru.netology.markers.databinding.FragmentWelcomeBinding

class WelcomeFragment : Fragment() {

    private lateinit var binding : FragmentWelcomeBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        initBinding(inflater, container)
        setupListeners()

        return binding.root
    }

    private fun initBinding(inflater: LayoutInflater, container: ViewGroup?) {
        binding = FragmentWelcomeBinding.inflate(
            inflater,
            container,
            false
        )
    }
    private fun setupListeners() {
        binding.apply {
            mapsButton.setOnClickListener {
                findNavController().navigate(R.id.welcomeFragmentToMapFragment)
            }
            listButton.setOnClickListener {
                findNavController().navigate(R.id.welcomeFragmentToListFragment)
            }
        }

    }

}