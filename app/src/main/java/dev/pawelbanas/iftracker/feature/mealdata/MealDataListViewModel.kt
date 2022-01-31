package dev.pawelbanas.iftracker.feature.mealdata

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.pawelbanas.iftracker.core.db.entity.MealData
import dev.pawelbanas.iftracker.feature.mealdata.domain.MealDataRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.Duration
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.time.format.TextStyle
import java.time.temporal.ChronoUnit
import java.util.*
import javax.inject.Inject

@HiltViewModel
class MealDataListViewModel @Inject constructor(
    private val mealDataRepository: MealDataRepository
) : ViewModel() {

    fun getMealsData(): Flow<List<UiMealData>> =
        mealDataRepository.getAllMealData().map { it.map(MealData::toUiMealData) }
}

fun MealData.toUiMealData() = UiMealData(
    dayOfWeekName = firstMealTime.dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.getDefault()),
    monthWithDay = firstMealTime.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT)),
    formattedFirstMealTime = firstMealTime.format(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT)),
    formattedLastMealTime = lastMealTime.format(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT)),
    goal = "${goal.toHours()}h",
    timeDifference = firstMealTime.formattedTimeDifferenceBetween(lastMealTime),
    isGoalMet = ChronoUnit.MINUTES.between(firstMealTime, lastMealTime) <= goal.toMinutes()
)

private fun LocalDateTime.formattedTimeDifferenceBetween(another: LocalDateTime): String {
    val totalDiffInMinutes = ChronoUnit.MINUTES.between(this, another)
    val minutesInHour = Duration.ofHours(1).toMinutes()
    val hours = totalDiffInMinutes / minutesInHour
    val minutes = totalDiffInMinutes % minutesInHour
    return LocalTime.of(hours.toInt(), minutes.toInt())
        .format(DateTimeFormatter.ofPattern("hh:mm"))
}