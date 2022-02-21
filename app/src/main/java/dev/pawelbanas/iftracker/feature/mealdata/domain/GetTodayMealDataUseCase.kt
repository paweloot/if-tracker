package dev.pawelbanas.iftracker.feature.mealdata.domain

import dev.pawelbanas.iftracker.core.db.entity.MealData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTodayMealDataUseCase @Inject constructor(private val mealDataRepository: MealDataRepository) {

    operator fun invoke(): Flow<MealData?> = mealDataRepository.getTodayMealDataAsFlow()
}