package dev.pawelbanas.iftracker.feature.mealdatalist

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import java.time.Duration
import java.time.LocalDateTime
import javax.inject.Inject

interface MealDataRepository {
    fun getAllMealData(): Flow<List<MealData>>
}

class DatabaseMealDataRepository @Inject constructor() : MealDataRepository {
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