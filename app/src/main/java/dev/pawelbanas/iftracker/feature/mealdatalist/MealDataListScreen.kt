package dev.pawelbanas.iftracker.feature.mealdatalist

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import dev.pawelbanas.iftracker.R
import dev.pawelbanas.iftracker.ui.theme.Dimens
import dev.pawelbanas.iftracker.ui.theme.Elevations

@Composable
fun MealDataListScreen(viewModel: MealDataListViewModel = viewModel()) {
    val mealsData by viewModel.mealsData.collectAsState(initial = emptyList())

    LazyColumn(modifier = Modifier.padding(all = Dimens.medium)) {
        items(mealsData) {
            MealDataRow(mealData = it)
        }
    }
}

@Composable
fun MealDataRow(mealData: UiMealData) {
    Card(
        elevation = Elevations.small,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = mealData.dayOfWeekName, style = MaterialTheme.typography.h6)
                Text(text = mealData.monthWithDay, style = MaterialTheme.typography.caption)
            }

            CaptionTextColumn(caption = stringResource(R.string.meal_data_caption_first_meal), text = mealData.formattedFirstMealTime)
            CaptionTextColumn(caption = stringResource(R.string.meal_data_caption_last_meal), text = mealData.formattedLastMealTime)
            CaptionTextColumn(caption = stringResource(R.string.meal_data_caption_goal), text = mealData.goal)

            val tint = if (mealData.isGoalMet) Color.Green else Color.Red
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Image(
                    painter = painterResource(id = R.drawable.ic_goal_achieved), contentDescription = "",
                    colorFilter = ColorFilter.tint(tint)
                )
                Text(
                    text = mealData.timeDifference,
                    style = MaterialTheme.typography.caption,
                    color = tint
                )
            }
        }
    }
}

@Composable
private fun CaptionTextColumn(caption: String, text: String) {
    Column {
        Text(text = caption, style = MaterialTheme.typography.caption)
        Text(text = text)
    }
}