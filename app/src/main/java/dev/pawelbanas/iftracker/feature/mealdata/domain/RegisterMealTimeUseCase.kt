package dev.pawelbanas.iftracker.feature.mealdata.domain

import dev.pawelbanas.iftracker.core.db.entity.MealData
import java.time.LocalDate
import java.time.LocalDateTime
import javax.inject.Inject

class RegisterMealTimeUseCase @Inject constructor(private val mealDataRepository: MealDataRepository) {

    suspend operator fun invoke() {
        val todayMealData = mealDataRepository.getByDate(LocalDate.now())
        if (todayMealData == null) {
            registerFirstMealTime()
        } else {
            registerLastMealTime(todayMealData)
        }
    }

    private suspend fun registerFirstMealTime() {
        val newMealData = MealData(firstMealTime = LocalDateTime.now())
        mealDataRepository.insert(newMealData)
    }

    private suspend fun registerLastMealTime(todayMealData: MealData) {
        val completeMealData = todayMealData.copy(lastMealTime = LocalDateTime.now())
        mealDataRepository.update(completeMealData)
    }
}