package dev.pawelbanas.iftracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import dev.pawelbanas.iftracker.feature.mealdata.MealDataListScreen
import dev.pawelbanas.iftracker.feature.mealdata.MealDataListViewModel
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
            FloatingActionButton(
                onClick = {
                    when (navController.currentBackStackEntry?.destination?.route) {
                        Screen.MealDataList.route -> mainViewModel.registerMealTime()
                    }
                }) {
                Icon(painter = painterResource(id = R.drawable.ic_start_eating), contentDescription = "Mark first meal")
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
