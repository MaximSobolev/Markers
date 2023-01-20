package ru.netology.markers.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import ru.netology.markers.dao.MarkerDao
import ru.netology.markers.dto.Marker
import ru.netology.markers.entity.MarkerEntity

class MarkerRepositoryImpl(private val markerDao: MarkerDao) : MarkerRepository {

    override fun getAll(): LiveData<List<Marker>> = markerDao.getAll().map { list ->
        list.map { it.toDto() }
    }

    override suspend fun delete(id: Long) {
        markerDao.delete(id)
    }

    override suspend fun edit(id: Long, name: String, description: String) {
        markerDao.updateTextById(id, name, description)
    }

    override suspend fun save(marker: Marker) {
        markerDao.insert(MarkerEntity.fromDto(marker))
    }
}