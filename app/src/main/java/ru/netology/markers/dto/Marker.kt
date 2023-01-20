package ru.netology.markers.dto

data class Marker (
    val id : Long,
    val x : Float,
    val y : Float,
    val name : String,
    val description : String,
    val showMarker : Boolean = false
        )