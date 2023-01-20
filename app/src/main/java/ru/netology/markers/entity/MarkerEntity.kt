package ru.netology.markers.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.netology.markers.dto.Marker

@Entity
data class MarkerEntity (
    @PrimaryKey (autoGenerate = true)
    val id : Long,
    val x : Float,
    val y : Float,
    val name : String,
    val description : String,
        ) {
    fun toDto() = Marker(id, x, y, name, description)
    companion object {
        fun fromDto(marker: Marker) = MarkerEntity(marker.id, marker.x, marker.y,
            marker.name, marker.description)
    }
}
