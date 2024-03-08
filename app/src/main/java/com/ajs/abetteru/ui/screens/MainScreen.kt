package com.ajs.abetteru.ui.screens

import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.navigation.compose.rememberNavController
import com.ajs.abetteru.navigation.AppNavigation
import com.ajs.abetteru.ui.comman.AppBar
import com.ajs.abetteru.ui.theme.ABetterUTheme


@Composable
fun MainScreen(
    modifier: Modifier = Modifier
) {
    val navHostController = rememberNavController()
    Scaffold(
        modifier = modifier,
        bottomBar = {
            AppBar()
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