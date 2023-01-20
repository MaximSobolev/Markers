package ru.netology.markers.recyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import ru.netology.markers.databinding.CardMarkerBinding
import ru.netology.markers.dto.Marker


class MarkerAdapter(
    private val onInteractionListener: OnInteractionListener
) : ListAdapter<Marker, MarkerViewHolder>(MarkerDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarkerViewHolder {
        val binding = CardMarkerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MarkerViewHolder(binding, onInteractionListener)
    }

    override fun onBindViewHolder(holder: MarkerViewHolder, position: Int) {
        val marker = getItem(position)
        holder.bind(marker)
    }
}