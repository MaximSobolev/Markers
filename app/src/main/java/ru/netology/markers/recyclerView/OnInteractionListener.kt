package ru.netology.markers.recyclerView

import ru.netology.markers.dto.Marker

interface OnInteractionListener {
    fun delete(marker: Marker)
    fun edit(marker: Marker)
    fun showOnMap(marker: Marker)
}