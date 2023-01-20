package ru.netology.markers.recyclerView

import android.view.View
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import ru.netology.markers.databinding.CardMarkerBinding
import ru.netology.markers.dto.Marker

class MarkerViewHolder(
    private val binding: CardMarkerBinding,
    private val onInteractionListener: OnInteractionListener
) : RecyclerView.ViewHolder(binding.root) {

    private var itemMarker : Marker? = null
    private val deleteListener : View.OnClickListener =
        View.OnClickListener {
            itemMarker?.let {
                binding.buttonGroup.visibility = View.GONE
                onInteractionListener.delete(it)
            }
        }
    private val editListener : View.OnClickListener =
        View.OnClickListener {
            itemMarker?.let {
                onInteractionListener.edit(it)
            }
        }
    private val showOnMapListener : View.OnClickListener =
        View.OnClickListener {
            itemMarker?.let {
                onInteractionListener.showOnMap(it)
            }
        }

    init {
        binding.apply {
            deleteButton.setOnClickListener(deleteListener)
            editButton.setOnClickListener(editListener)
            showOnMap.setOnClickListener(showOnMapListener)
            cardView.setOnClickListener{
                buttonGroup.isVisible = !buttonGroup.isVisible
            }
        }
    }

    fun bind (marker: Marker) {
        binding.apply {
            name.text = marker.name
            description.text = marker.description
            itemMarker = marker
        }
    }
}