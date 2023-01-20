package ru.netology.markers.recyclerView

import androidx.recyclerview.widget.DiffUtil
import ru.netology.markers.dto.Marker

class MarkerDiffCallBack : DiffUtil.ItemCallback<Marker>() {
    override fun areItemsTheSame(oldItem: Marker, newItem: Marker): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Marker, newItem: Marker): Boolean {
        return oldItem == newItem
    }
}