package dev.pawelbanas.iftracker.feature.mealdata.ui

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
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import dev.pawelbanas.iftracker.R
import dev.pawelbanas.iftracker.ui.theme.Dimens
import dev.pawelbanas.iftracker.ui.theme.Elevations

@Composable
fun MealDataListScreen(viewModel: MealDataListViewModel) {
    val mealsData by viewModel.getMealsData().collectAsState(initial = emptyList())

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                top = Dimens.medium,
                start = Dimens.medium,
                end = Dimens.medium
            ),
        verticalArrangement = Arrangement.spacedBy(Dimens.small)
    ) {
        items(mealsData) {
            MealDataRow(mealData = it)
        }
    }
}

@Composable
fun MealDataRow(mealData: UiMealData, modifier: Modifier = Modifier) {
    Card(
        elevation = Elevations.tiny,
        modifier = modifier
            .fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier.padding(vertical = Dimens.small)
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = mealData.dayOfWeekName, style = MaterialTheme.typography.h6)
                Text(text = mealData.monthWithDay, style = MaterialTheme.typography.caption)
            }

            CaptionTextColumn(caption = stringResource(R.string.meal_data_caption_first_meal), text = mealData.formattedFirstMealTime)
            CaptionTextColumn(caption = stringResource(R.string.meal_data_caption_last_meal), text = mealData.formattedLastMealTime)
            CaptionTextColumn(caption = stringResource(R.string.meal_data_caption_goal), text = mealData.goal)

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.defaultMinSize(minWidth = 32.dp)
            ) {
                Image(
                    painter = painterResource(id = mealData.goalStatus.iconResId),
                    contentDescription = "",
                    colorFilter = ColorFilter.tint(mealData.goalStatus.iconTint)
                )
                Text(
                    text = mealData.timeDifference,
                    style = MaterialTheme.typography.caption,
                    color = mealData.goalStatus.iconTint
                )
            }
        }
    }
}

@Composable
private fun CaptionTextColumn(caption: String, text: String, modifier: Modifier = Modifier) {
    Column(modifier) {
        Text(text = caption, style = MaterialTheme.typography.caption)
        Text(text = text)
    }
}