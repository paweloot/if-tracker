package dev.pawelbanas.iftracker.feature.mealdatalist

data class UiMealData(
    val dayOfWeekName: String,
    val monthWithDay: String,
    val formattedFirstMealTime: String,
    val formattedLastMealTime: String,
    val goal: String,
    val timeDifference: String,
    val isGoalMet: Boolean
)