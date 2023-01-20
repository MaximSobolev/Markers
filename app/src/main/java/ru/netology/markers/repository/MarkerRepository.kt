package ru.netology.markers.repository

import androidx.lifecycle.LiveData
import ru.netology.markers.dto.Marker

interface MarkerRepository {
    fun getAll() : LiveData<List<Marker>>
    suspend fun delete(id : Long)
    suspend fun edit(id : Long, name: String, description : String)
    suspend fun save(marker: Marker)
}