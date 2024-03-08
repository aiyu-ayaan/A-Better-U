package com.ajs.abetteru.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ajs.abetteru.navigation.AffirmationScreenRoute
import com.ajs.abetteru.navigation.AppNavigation
import com.ajs.abetteru.navigation.JournalScreenRoute
import com.ajs.abetteru.navigation.VisionBoardScreenRoute
import com.ajs.abetteru.ui.comman.AppBar
import com.ajs.abetteru.ui.theme.ABetterUTheme


@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    navHostController: NavHostController = rememberNavController()
) {
    val visibleScreens = listOf(
        JournalScreenRoute.JournalScreen.route,
        AffirmationScreenRoute.AffirmationScreen.route,
        VisionBoardScreenRoute.VisionBoardScreen.route
    )
    val backStackEntry by navHostController.currentBackStackEntryAsState()
    Scaffold(
        modifier = modifier,
        bottomBar = {
            val currentDestination = backStackEntry?.destination
            val isTheir = visibleScreens.any { it == currentDestination?.route }
            val density = LocalDensity.current
            AnimatedVisibility(
                visible = isTheir,
                enter = slideInVertically {
                    with(density) { -40.dp.roundToPx() }
                } + expandVertically(
                    expandFrom = Alignment.Top
                ) + fadeIn(
                    initialAlpha = 0.3f
                ),
                exit = slideOutVertically() + shrinkVertically() + fadeOut()
            ) {
                AppBar(
                    backStackEntry = navHostController.currentBackStackEntryAsState(),
                    onClick = {
                        navHostController.navigate(it)
                    }
                )
            }
        }
    ) {
        AppNavigation(
            navHostController = navHostController,
            modifier = modifier.padding(
                start = it.calculateStartPadding(layoutDirection = LayoutDirection.Ltr),
                end = it.calculateStartPadding(layoutDirection = LayoutDirection.Rtl),
                bottom = it.calculateBottomPadding()
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun MainScreenPreview() {
    ABetterUTheme {
        MainScreen()
    }
}