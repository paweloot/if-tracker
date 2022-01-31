package dev.pawelbanas.iftracker.feature.mealdata.ui

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import dev.pawelbanas.iftracker.R
import java.time.Duration
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

sealed class GoalStatus(val iconTint: Color, @DrawableRes val iconResId: Int) {
    object Incomplete : GoalStatus(Color.Gray, R.drawable.ic_goal_pending)
    object Achieved : GoalStatus(Color.Green, R.drawable.ic_goal_achieved)
    object Failed : GoalStatus(Color.Red, R.drawable.ic_goal_failed)

    companion object {
        fun fromMealTimeData(firstMealTime: LocalDateTime, lastMealTime: LocalDateTime?, goal: Duration) =
            if (lastMealTime == null) {
                Incomplete
            } else {
                val diff = ChronoUnit.MINUTES.between(firstMealTime, lastMealTime)
                if (diff <= goal.toMinutes()) Achieved else Failed
            }
    }
}