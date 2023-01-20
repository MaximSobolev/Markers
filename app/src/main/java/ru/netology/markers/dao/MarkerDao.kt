package ru.netology.markers.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.netology.markers.entity.MarkerEntity

@Dao
interface MarkerDao {
    @Query("SELECT * FROM MarkerEntity ORDER BY id DESC")
    fun getAll() : LiveData<List<MarkerEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(marker: MarkerEntity)

    @Query("UPDATE MarkerEntity SET name = :name, description = :description WHERE id = :id")
    suspend fun updateTextById(id : Long, name : String, description : String)

    @Query("DELETE FROM MarkerEntity WHERE id = :id")
    suspend fun delete(id: Long)

    @Query("UPDATE MarkerEntity SET showMarker = 1 WHERE id = :id")
    suspend fun show(id: Long)
}