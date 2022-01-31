package dev.pawelbanas.iftracker.feature.mealdata

import java.time.Duration
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

data class UiMealData(
    val dayOfWeekName: String,
    val monthWithDay: String,
    val formattedFirstMealTime: String,
    val formattedLastMealTime: String,
    val goal: String,
    val timeDifference: String,
    val goalStatus: GoalStatus
)

enum class GoalStatus {
    MET, INCOMPLETE, NOT_MET;

    companion object {
        fun fromMealTimeData(firstMealTime: LocalDateTime, lastMealTime: LocalDateTime?, goal: Duration) =
            if (lastMealTime == null) {
                INCOMPLETE
            } else {
                val diff = ChronoUnit.MINUTES.between(firstMealTime, lastMealTime)
                if (diff <= goal.toMinutes()) MET else NOT_MET
            }
    }
}