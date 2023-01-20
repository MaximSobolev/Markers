package ru.netology.markers.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.netology.markers.dao.MarkerDao
import ru.netology.markers.entity.MarkerEntity

@Database(
    entities = [MarkerEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDb : RoomDatabase() {
    abstract fun markerDao() : MarkerDao

    companion object {
        @Volatile
        private var instance : AppDb? = null

        fun getInstance(context: Context) : AppDb {
            return instance ?: synchronized(this) {
                instance ?: buildDataBase(context).also{ instance = it }
            }
        }

        fun buildDataBase(context: Context) =
            Room.databaseBuilder(context, AppDb::class.java, "app.db")
                .build()
    }
}