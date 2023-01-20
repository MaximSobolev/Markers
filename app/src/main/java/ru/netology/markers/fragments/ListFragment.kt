package ru.netology.markers.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ListAdapter
import ru.netology.markers.R
import ru.netology.markers.databinding.FragmentListBinding
import ru.netology.markers.dialog.EditDialogFragment
import ru.netology.markers.dto.Marker
import ru.netology.markers.recyclerView.MarkerAdapter
import ru.netology.markers.recyclerView.OnInteractionListener
import ru.netology.markers.utill.PointXArg
import ru.netology.markers.utill.PointYArg
import ru.netology.markers.viewModel.MarkerViewModel

class ListFragment : Fragment() {

    private lateinit var binding: FragmentListBinding
    private lateinit var adapter: MarkerAdapter
    private val viewModel : MarkerViewModel by viewModels(ownerProducer = ::requireParentFragment)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        initBinding(inflater, container)
        initAdapter()
        setupObserve()
        setupListeners()
        return binding.root
    }

    private fun initBinding(inflater: LayoutInflater, container: ViewGroup?) {
        binding = FragmentListBinding.inflate(
            inflater,
            container,
            false
        )
    }

    private fun initAdapter() {
        adapter = MarkerAdapter(
            object : OnInteractionListener {
                override fun delete(marker: Marker) {
                    viewModel.delete(marker)
                }

                override fun edit(marker: Marker) {
                    EditDialogFragment(marker).show(parentFragmentManager, "edit_dialog")
                }

                override fun showOnMap(marker: Marker) {
                    findNavController().navigate(R.id.listFragmentToMapFragment,
                        Bundle().apply {
                            pointXArg = marker.x
                            pointYArg = marker.y
                        }
                    )
                }

            }
        )
        binding.list.adapter = adapter

    }

    private fun setupObserve() {
        viewModel.data.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }
    private fun setupListeners() {
        binding.apply {

        }
    }

    companion object {
        var Bundle.pointXArg : Float? by PointXArg
        var Bundle.pointYArg : Float? by PointYArg
    }
}