package dev.pawelbanas.iftracker

sealed class Screen(val route: String) {
    object MealDataList : Screen("mealDataList")
}