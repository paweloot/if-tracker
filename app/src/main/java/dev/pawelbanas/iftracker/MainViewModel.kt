package dev.pawelbanas.iftracker

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.pawelbanas.iftracker.feature.mealdata.domain.RegisterMealTimeUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val registerMealTimeUseCase: RegisterMealTimeUseCase
) : ViewModel() {

    fun registerMealTime() = viewModelScope.launch {
        registerMealTimeUseCase()
    }
}