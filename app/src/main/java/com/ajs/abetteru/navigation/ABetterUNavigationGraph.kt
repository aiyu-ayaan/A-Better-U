package com.ajs.abetteru.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import com.ajs.abetteru.ui.screens.MainScreen
import com.ajs.abetteru.ui.screens.affirmation.compose.AffirmationScreen
import com.ajs.abetteru.ui.screens.journal.add_edit.AddEditViewModel
import com.ajs.abetteru.ui.screens.journal.add_edit.compose.AddEditScreen
import com.ajs.abetteru.ui.screens.journal.main.JournalViewModel
import com.ajs.abetteru.ui.screens.journal.main.compose.JournalScreen
import com.ajs.abetteru.ui.screens.vision_board.compose.VisionBoardScreen
import com.ajs.abetteru.ui.theme.ThemeEvents
import com.ajs.abetteru.utils.animatedComposable
import com.ajs.abetteru.utils.fadeThroughComposable


enum class TopLevelRoute(val route: String) {
    HOME("home"),
}

sealed class ABetterUNavigation(val route: String) {
    data object MainScreens : ABetterUNavigation(TopLevelRoute.HOME.route)

}


@Composable
fun ABetterUNavigationGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    onEvent: (ThemeEvents) -> Unit
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = ABetterUNavigation.MainScreens.route
    ) {
        animatedComposable(
            route = ABetterUNavigation.MainScreens.route
        ) {
            MainScreen(onEvent = onEvent)
        }
    }
}


sealed class Screen(val route: String) {
    data object Journal : Screen("Journal")
    data object Affirmation : Screen("Affirmation")
    data object VisionBoard : Screen("VisionBoard")
}

@Composable
fun AppNavigation(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    onEvent: (ThemeEvents) -> Unit = {},
    startDestination: String = Screen.Journal.route
) {
    NavHost(
        modifier = modifier,
        navController = navHostController,
        startDestination = startDestination
    ) {
        journalGraph(
            navController = navHostController,
            onEvent = onEvent
        )
        affirmationGraph(navHostController)
        visionBoardGraph(navHostController)
    }
}

sealed class JournalScreenRoute(val route: String) {
    data object JournalScreen : JournalScreenRoute("journal_screen")
    data object AddEditScreen : JournalScreenRoute("add_edit_screen")
}


fun NavGraphBuilder.journalGraph(
    navController: NavHostController,
    onEvent: (ThemeEvents) -> Unit = {},
) {
    navigation(
        startDestination = JournalScreenRoute.JournalScreen.route,
        route = Screen.Journal.route
    ) {
        fadeThroughComposable(
            route = JournalScreenRoute.JournalScreen.route
        ) {
            val viewModel: JournalViewModel = hiltViewModel()
            val journals by viewModel.journals.collectAsState(initial = emptyList())
            JournalScreen(
                navController = navController,
                journals = journals,
                onEvent = onEvent
            )
        }
        animatedComposable(
            route = JournalScreenRoute.AddEditScreen.route + "?modelId={modelId}",
            arguments = listOf(
                navArgument("modelId") {
                    defaultValue = -1
                    type = NavType.IntType
                }
            )
        ) {
            val viewModel: AddEditViewModel = hiltViewModel()
            val color by viewModel.color
            val question by viewModel.question
            val answer by viewModel.answer
            val bitmap by viewModel.bitmap
            val isNewEntry = viewModel.id == -1
            AddEditScreen(
                navController = navController,
                question = question,
                bitmap = bitmap,
                answer = answer,
                onThemeEvents = onEvent,
                color = color,
                isNewEntry = isNewEntry,
                onEvent = viewModel::onEvent
            )
        }
    }
}

sealed class AffirmationScreenRoute(val route: String) {
    data object AffirmationScreen : AffirmationScreenRoute("affirmation_screen")
}

fun NavGraphBuilder.affirmationGraph(
    navController: NavHostController
) {
    navigation(
        startDestination = AffirmationScreenRoute.AffirmationScreen.route,
        route = Screen.Affirmation.route
    ) {
        fadeThroughComposable(
            route = AffirmationScreenRoute.AffirmationScreen.route
        ) {
            AffirmationScreen(
                navController = navController
            )
        }
    }
}
//

sealed class VisionBoardScreenRoute(val route: String) {
    data object VisionBoardScreen : VisionBoardScreenRoute("vision_board_screen")
}

fun NavGraphBuilder.visionBoardGraph(
    navController: NavHostController
) {
    navigation(
        startDestination = VisionBoardScreenRoute.VisionBoardScreen.route,
        route = Screen.VisionBoard.route
    ) {
        fadeThroughComposable(
            route = VisionBoardScreenRoute.VisionBoardScreen.route
        ) {
            VisionBoardScreen(
                navController = navController
            )
        }
    }
}

