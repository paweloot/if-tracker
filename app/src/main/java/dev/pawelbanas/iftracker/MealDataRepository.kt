package dev.pawelbanas.iftracker

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import java.time.Duration
import java.time.LocalDateTime

interface MealDataRepository {
    fun getAllMealData(): Flow<List<MealData>>
}

class DatabaseMealDataRepository : MealDataRepository {
    override fun getAllMealData(): Flow<List<MealData>> = flowOf(
        listOf(
            MealData(
                LocalDateTime.now(),
                LocalDateTime.now().plusHours(9),
                Duration.ofHours(9)
            )
        )
    )
}