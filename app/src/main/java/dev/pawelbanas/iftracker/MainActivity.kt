package dev.pawelbanas.iftracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import dev.pawelbanas.iftracker.feature.mealdata.ui.MealDataListScreen
import dev.pawelbanas.iftracker.feature.mealdata.ui.MealDataListViewModel
import dev.pawelbanas.iftracker.ui.theme.IFTrackerTheme

@AndroidEntryPoint
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
fun IFTrackerScaffold(mainViewModel: MainViewModel = viewModel()) {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomAppBar(
                cutoutShape = MaterialTheme.shapes.small.copy(CornerSize(percent = 50))
            ) {}
        },
        floatingActionButton = {
            val todayMealData by mainViewModel.todayMealData.collectAsState(initial = null)
            val iconResource = if (todayMealData == null) R.drawable.ic_start_eating else R.drawable.ic_stop_eating

            Button(
                enabled = todayMealData?.lastMealTime == null,
                modifier = Modifier.defaultMinSize(minWidth = 56.dp, minHeight = 56.dp),
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.secondary),
                onClick = {
                    when (navController.currentBackStackEntry?.destination?.route) {
                        Screen.MealDataList.route -> mainViewModel.registerMealTime()
                    }
                }) {
                Icon(
                    painter = painterResource(id = iconResource),
                    contentDescription = null
                )
            }
        },
        floatingActionButtonPosition = FabPosition.Center,
        isFloatingActionButtonDocked = true
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.MealDataList.route,
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            composable(Screen.MealDataList.route) {
                val viewModel = hiltViewModel<MealDataListViewModel>()
                MealDataListScreen(viewModel)
            }
        }
    }
}
