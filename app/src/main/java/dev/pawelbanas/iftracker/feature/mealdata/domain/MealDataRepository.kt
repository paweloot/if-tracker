package dev.pawelbanas.iftracker.feature.mealdata.domain

import dev.pawelbanas.iftracker.core.db.entity.MealData
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

interface MealDataRepository {
    fun getAllMealData(): Flow<List<MealData>>
    suspend fun getByDate(localDate: LocalDate): MealData?
    suspend fun insert(mealData: MealData)
    suspend fun update(mealData: MealData)
}