package dev.pawelbanas.iftracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import dev.pawelbanas.iftracker.ui.theme.Dimens
import dev.pawelbanas.iftracker.ui.theme.Elevations
import dev.pawelbanas.iftracker.ui.theme.IFTrackerTheme
import java.time.Duration
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.time.format.TextStyle
import java.time.temporal.ChronoUnit
import java.util.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IFTrackerTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    IFTrackerScaffold()
                }
            }
        }
    }
}

@Composable
fun IFTrackerScaffold() {
    Scaffold(
        bottomBar = {
            BottomAppBar(
                cutoutShape = MaterialTheme.shapes.small.copy(CornerSize(percent = 50))
            ) {}
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /*TODO*/ }) {
                Icon(painter = painterResource(id = R.drawable.ic_start_eating), contentDescription = "Mark first meal")
            }
        },
        floatingActionButtonPosition = FabPosition.Center,
        isFloatingActionButtonDocked = true
    ) {
        MainScreen()
    }
}

@Composable
fun MainScreen(viewModel: MainViewModel = viewModel()) {
    val mealsData by viewModel.mealsData.collectAsState(initial = emptyList())

    LazyColumn(modifier = Modifier.padding(all = Dimens.medium)) {
        items(mealsData) {
            MealDataRow(mealData = it.toUiMealData())
        }
    }
}

data class UiMealData(
    val dayOfWeekName: String,
    val monthWithDay: String,
    val formattedFirstMealTime: String,
    val formattedLastMealTime: String,
    val goal: String,
    val timeDifference: String,
    val isGoalMet: Boolean
)

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

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
}