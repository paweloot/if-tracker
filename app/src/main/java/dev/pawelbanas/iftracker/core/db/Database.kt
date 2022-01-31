package dev.pawelbanas.iftracker.core.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import dev.pawelbanas.iftracker.core.db.dao.MealDataDao
import dev.pawelbanas.iftracker.core.db.entity.MealData

@Database(entities = [MealData::class], version = 1)
@TypeConverters(Converters::class)
abstract class IfTrackerDatabase : RoomDatabase() {
    abstract fun mealDataDao(): MealDataDao
}