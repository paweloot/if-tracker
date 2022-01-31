package dev.pawelbanas.iftracker.feature.mealdata.ui

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.pawelbanas.iftracker.core.db.entity.MealData
import dev.pawelbanas.iftracker.feature.mealdata.domain.MealDataRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class MealDataListViewModel @Inject constructor(
    private val mealDataRepository: MealDataRepository
) : ViewModel() {

    fun getMealsData(): Flow<List<UiMealData>> =
        mealDataRepository.getAllMealData().map { it.map(MealData::toUiMealData) }
}
