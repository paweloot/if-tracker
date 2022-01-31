package dev.pawelbanas.iftracker.core.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.Duration
import java.time.LocalDateTime

@Entity
data class MealData(
    @PrimaryKey(autoGenerate = true) val mealDataId: Int = 0,
    val firstMealTime: LocalDateTime,
    val lastMealTime: LocalDateTime,
    val goal: Duration
)