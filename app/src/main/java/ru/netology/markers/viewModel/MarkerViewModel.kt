package ru.netology.markers.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.yandex.mapkit.geometry.Point
import kotlinx.coroutines.launch
import ru.netology.markers.db.AppDb
import ru.netology.markers.dto.Marker
import ru.netology.markers.repository.MarkerRepository
import ru.netology.markers.repository.MarkerRepositoryImpl

class MarkerViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: MarkerRepository = MarkerRepositoryImpl(
        AppDb.getInstance(application).markerDao()
    )

    val data = repository.getAll()

    private var emptyPoint: Point? = null


    fun delete(marker: Marker) {
        viewModelScope.launch {
            repository.delete(marker.id)
        }
    }

    fun edit(id: Long, name: String, description: String) {
        viewModelScope.launch {
            repository.edit(id, name, description)
        }
    }

    fun editPoint(point: Point) {
        emptyPoint = point
    }

    fun save(name: String, description: String) {
        viewModelScope.launch {
            emptyPoint?.let {
                val x = emptyPoint?.latitude?.toFloat()
                val y = emptyPoint?.longitude?.toFloat()
                if (x != null && y != null) {
                    val marker = Marker(0, x, y, name, description)
                    repository.save(marker)
                    emptyPoint = null
                }
            }
        }
    }

}