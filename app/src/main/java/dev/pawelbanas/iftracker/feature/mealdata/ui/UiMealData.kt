package dev.pawelbanas.iftracker.feature.mealdata.ui

import dev.pawelbanas.iftracker.core.db.entity.MealData
import java.time.Duration
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.time.format.TextStyle
import java.time.temporal.ChronoUnit
import java.util.*

data class UiMealData(
    val dayOfWeekName: String,
    val monthWithDay: String,
    val formattedFirstMealTime: String,
    val formattedLastMealTime: String,
    val goal: String,
    val timeDifference: String,
    val goalStatus: GoalStatus
)

fun MealData.toUiMealData() = UiMealData(
    dayOfWeekName = firstMealTime.dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.getDefault()),
    monthWithDay = firstMealTime.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT)),
    formattedFirstMealTime = firstMealTime.format(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT)),
    formattedLastMealTime = lastMealTime?.format(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT)) ?: "-",
    goal = "${goal.toHours()}h",
    timeDifference = lastMealTime?.let { firstMealTime.formattedTimeDifferenceBetween(it) } ?: "-",
    goalStatus = GoalStatus.fromMealTimeData(firstMealTime, lastMealTime, goal)
)

private fun LocalDateTime.formattedTimeDifferenceBetween(another: LocalDateTime): String {
    val totalDiffInMinutes = ChronoUnit.MINUTES.between(this, another)
    val minutesInHour = Duration.ofHours(1).toMinutes()
    val hours = totalDiffInMinutes / minutesInHour
    val minutes = totalDiffInMinutes % minutesInHour
    return "${hours.padWithZero()}:${minutes.padWithZero()}"
}

private fun Long.padWithZero() = toString().padStart(2, '0')
