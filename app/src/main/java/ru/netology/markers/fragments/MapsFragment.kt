package ru.netology.markers.fragments

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.core.view.MenuProvider
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.yandex.mapkit.Animation
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.*
import com.yandex.mapkit.map.Map
import com.yandex.mapkit.mapview.MapView
import com.yandex.runtime.image.ImageProvider
import ru.netology.markers.R
import ru.netology.markers.databinding.FragmentMapsBinding
import ru.netology.markers.dialog.AddMarkerFragment
import ru.netology.markers.utill.PointXArg
import ru.netology.markers.utill.PointYArg
import ru.netology.markers.utill.getBitmapFromVectorDrawable
import ru.netology.markers.viewModel.MarkerViewModel

class MapsFragment : Fragment() {
    private lateinit var binding: FragmentMapsBinding
    private val viewModel : MarkerViewModel by viewModels(ownerProducer = ::requireParentFragment)
    private lateinit var mapView: MapView
    private lateinit var markerCollection: MapObjectCollection

    private val inputListener = object : InputListener {
        override fun onMapTap(p0: Map, p1: Point) {
            viewModel.editPoint(p1)
            AddMarkerFragment().show(parentFragmentManager, "dialog")
        }

        override fun onMapLongTap(p0: Map, p1: Point) {
            return
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        initBinding(inflater, container)
        setupMenu()
        setupListeners()
        setupMarker()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initMaps()
    }


    override fun onStart() {
        super.onStart()
        MapKitFactory.getInstance().onStart()
        mapView.onStart()
    }

    override fun onStop() {
        super.onStop()
        MapKitFactory.getInstance().onStop()
        mapView.onStop()
    }

    private fun initBinding(inflater: LayoutInflater, container: ViewGroup?) {
        binding = FragmentMapsBinding.inflate(
            inflater,
            container,
            false
        )
    }

    private fun initMaps() {
        MapKitFactory.initialize(requireContext())
        mapView = requireView().findViewById(R.id.map)
        markerCollection = mapView.map.mapObjects.addCollection()
        mapView.map.isZoomGesturesEnabled
        val x = arguments?.pointXArg
        val y = arguments?.pointYArg
        if (x == null || y == null) {
            mapView.map.move(
                CameraPosition(Point(56.802780, 60.484871), 5.0f, 0.0f, 0.0f)
            )
        } else {
            mapView.map.move(
                CameraPosition(Point(x.toDouble(), y.toDouble()), 17.0f, 0.0f, 0.0f),
                Animation(Animation.Type.SMOOTH, 4f),
                null
            )
        }
        mapView.map.addInputListener(inputListener)
    }

    private fun setupListeners() {
        binding.apply {
            zoomPlus.setOnClickListener {
                mapView.map.move(
                    CameraPosition(mapView.map.cameraPosition.target, mapView.map.cameraPosition.zoom + 1, 0f, 0f),
                    Animation(Animation.Type.SMOOTH, 0.3f),
                    null
                )
            }
            zoomMinus.setOnClickListener {
                mapView.map.move(
                    CameraPosition(mapView.map.cameraPosition.target, mapView.map.cameraPosition.zoom - 1, 0f, 0f),
                    Animation(Animation.Type.SMOOTH, 0.3f),
                    null
                )
            }
        }
    }

    private fun setupMarker() {
        viewModel.data.observe(viewLifecycleOwner) {
            it.map { mark ->
                markerCollection.addPlacemark(
                    Point(mark.x.toDouble(), mark.y.toDouble()),
                    ImageProvider.fromBitmap(requireContext().getBitmapFromVectorDrawable(R.drawable.ic_baseline_location_on_24))
                )
            }
        }
    }

    private fun setupMenu() {
        requireActivity().addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.options_maps_fragment, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean =
                if (menuItem.itemId == R.id.listMarker) {
                    findNavController().navigate(R.id.mapFragmentToListFragment)
                    true
                } else false
        }, viewLifecycleOwner)
    }

    companion object {
        var Bundle.pointXArg : Float? by PointXArg
        var Bundle.pointYArg : Float? by PointYArg
    }

}
