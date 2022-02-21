package dev.pawelbanas.iftracker

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.pawelbanas.iftracker.core.db.entity.MealData
import dev.pawelbanas.iftracker.feature.mealdata.domain.GetTodayMealDataUseCase
import dev.pawelbanas.iftracker.feature.mealdata.domain.RegisterMealTimeUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    getTodayMealDataUseCase: GetTodayMealDataUseCase,
    private val registerMealTimeUseCase: RegisterMealTimeUseCase
) : ViewModel() {

    val todayMealData: Flow<MealData?> = getTodayMealDataUseCase()

    fun registerMealTime() = viewModelScope.launch {
        registerMealTimeUseCase()
    }
}