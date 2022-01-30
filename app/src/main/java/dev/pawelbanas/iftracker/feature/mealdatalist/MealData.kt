package dev.pawelbanas.iftracker.feature.mealdatalist

import java.time.Duration
import java.time.LocalDateTime

data class MealData(
    val firstMealTime: LocalDateTime,
    val lastMealTime: LocalDateTime,
    val goal: Duration
)