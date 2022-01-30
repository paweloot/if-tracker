package dev.pawelbanas.iftracker

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow
import java.time.Duration
import java.time.LocalDateTime

class MainViewModel : ViewModel() {

    private val mealDataRepository: MealDataRepository = DatabaseMealDataRepository()
    val mealsData: Flow<List<MealData>> = mealDataRepository.getAllMealData()

}

data class MealData(
    val firstMealTime: LocalDateTime,
    val lastMealTime: LocalDateTime,
    val goal: Duration
)