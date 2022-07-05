package dev.pawelbanas.iftracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import dev.pawelbanas.iftracker.feature.mealdata.ui.MealDataListScreen
import dev.pawelbanas.iftracker.feature.mealdata.ui.MealDataListViewModel
import dev.pawelbanas.iftracker.ui.theme.Dimens
import dev.pawelbanas.iftracker.ui.theme.IFTrackerTheme

@ExperimentalMaterialApi
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

@ExperimentalMaterialApi
@Composable
fun IFTrackerScaffold(
    mainViewModel: MainViewModel = viewModel(),
    topSectionFraction: Float = 0.25f
) {
    val navController = rememberNavController()
    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = BottomSheetState(
            initialValue = BottomSheetValue.Expanded,
            animationSpec = SwipeableDefaults.AnimationSpec,
            confirmStateChange = { false }
        )
    )

    BottomSheetScaffold(
        sheetContent = {
            Box(
                Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(1f - topSectionFraction)
            ) {
                IFTrackerFrontLayer(navController = navController)
            }
        },
        sheetShape = RoundedCornerShape(topStart = Dimens.large),
        scaffoldState = scaffoldState,
        sheetGesturesEnabled = false,
        topBar = {
            TopHeader()
        },
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            FloatingActionButton(onClick = {}) {
                Icon(painterResource(id = R.drawable.ic_start_eating), contentDescription = null)
            }
        },
    ) {}

//    BackdropScaffold(
//        gesturesEnabled = true,
//        peekHeight = 96.dp,
//        backLayerContent = {
//            IFTrackerBackLayer(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(96.dp)
//            )
//        },
//        frontLayerContent = {
//            IFTrackerFrontLayer(
//                navController = navController,
//                modifier = Modifier.fillMaxSize()
//            )
//        },
//        frontLayerShape = RoundedCornerShape(topStart = Dimens.large),
//        appBar = {
//            Box(modifier = Modifier.size(0.dp))
//        }
//    )

//    Scaffold(
//        bottomBar = {
//            BottomAppBar(
//                cutoutShape = MaterialTheme.shapes.small.copy(CornerSize(percent = 50))
//            ) {}
//        },
//        floatingActionButton = {
//            val todayMealData by mainViewModel.todayMealData.collectAsState(initial = null)
//            val iconResource = if (todayMealData == null) R.drawable.ic_start_eating else R.drawable.ic_stop_eating
//
//            Button(
//                enabled = todayMealData?.lastMealTime == null,
//                modifier = Modifier.defaultMinSize(minWidth = 56.dp, minHeight = 56.dp),
//                shape = CircleShape,
//                colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.secondary),
//                onClick = {
//                    when (navController.currentBackStackEntry?.destination?.route) {
//                        Screen.MealDataList.route -> mainViewModel.registerMealTime()
//                    }
//                }) {
//                Icon(
//                    painter = painterResource(id = iconResource),
//                    contentDescription = null
//                )
//            }
//        },
//        floatingActionButtonPosition = FabPosition.Center,
//        isFloatingActionButtonDocked = true
//    ) { innerPadding ->
//
//    }
}

@Composable
fun IFTrackerBackLayer(modifier: Modifier = Modifier) {
    Column(modifier = modifier.border(4.dp, Color.Red)) {
        Text(text = "Testing back layer")
    }
}

@Composable
fun IFTrackerFrontLayer(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = Screen.MealDataList.route,
        modifier = modifier
    ) {
        composable(Screen.MealDataList.route) {
            val viewModel = hiltViewModel<MealDataListViewModel>()
            MealDataListScreen(viewModel)
        }
    }
}

@Composable
fun TopHeader(
    modifier: Modifier = Modifier,
    fraction: Float = 0.3f
) {
    Surface(
        color = MaterialTheme.colors.primarySurface,
        contentColor = contentColorFor(MaterialTheme.colors.primarySurface),
        modifier = modifier.fillMaxSize()
    ) {
        Box {
            Column(
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(fraction)
                    .padding(
                        vertical = Dimens.medium,
                        horizontal = Dimens.large
                    )
            ) {
                Text(
                    text = "Top header text",
                    style = MaterialTheme.typography.h2,
                )
            }
        }
    }
}
