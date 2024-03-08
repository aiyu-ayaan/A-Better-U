package com.ajs.abetteru.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.navigation
import com.ajs.abetteru.ui.screens.MainScreen
import com.ajs.abetteru.ui.screens.journal.compose.JournalScreen
import com.ajs.abetteru.utils.animatedComposable


enum class TopLevelRoute(val route: String) {
    HOME("home"),
}

sealed class ABetterUNavigation(val route: String) {
    data object MainScreens : ABetterUNavigation(TopLevelRoute.HOME.route)

}


@Composable
fun ABetterUNavigationGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = ABetterUNavigation.MainScreens.route
    ) {
        animatedComposable(
            route = ABetterUNavigation.MainScreens.route
        ) {
            MainScreen()
        }
    }
}


sealed class Screen(val route: String) {
    data object Journal : Screen("Journal")
}

@Composable
fun AppNavigation(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    startDestination: String = Screen.Journal.route
) {
    NavHost(
        modifier = modifier,
        navController = navHostController,
        startDestination = startDestination
    ) {
        journalGraph(navHostController)
    }
}

sealed class JournalScreenRoute(val route: String) {
    data object JournalScreen : JournalScreenRoute("journal_screen")
}


fun NavGraphBuilder.journalGraph(
    navController: NavHostController
) {
    navigation(
        startDestination = JournalScreenRoute.JournalScreen.route,
        route = Screen.Journal.route
    ) {
        animatedComposable(
            route = JournalScreenRoute.JournalScreen.route
        ) {
            JournalScreen()
        }
    }
}