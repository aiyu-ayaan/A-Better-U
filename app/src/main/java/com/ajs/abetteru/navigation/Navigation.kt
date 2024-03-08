package com.ajs.abetteru.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.ajs.abetteru.ui.comman.MainContainer
import com.ajs.abetteru.utils.animatedComposable

sealed class Screen(val route: String) {
    data object Home : Screen("home")

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Navigation(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        animatedComposable(
            route = Screen.Home.route
        ) {
            MainContainer()
        }

    }
}