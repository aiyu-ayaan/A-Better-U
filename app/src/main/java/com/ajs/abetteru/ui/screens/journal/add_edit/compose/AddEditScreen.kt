package com.ajs.abetteru.ui.screens.journal.add_edit.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ajs.abetteru.ui.comman.MainContainer
import com.ajs.abetteru.ui.theme.ABetterUTheme
import com.ajs.abetteru.ui.theme.ThemeEvents
import com.ajs.abetteru.ui.theme.spacing

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditScreen(
    modifier: Modifier = Modifier,
    onEvent: (ThemeEvents) -> Unit = {},
    color: Color = Color.Unspecified,
    navController: NavController = rememberNavController(),
) {
    MainContainer(
        modifier = modifier,
        appBarColor = color,
        onNavigationClick = {
            navController.popBackStack()
        },
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .background(color = color)
        ) {
            Column(
                modifier = Modifier
                    .padding(
                        horizontal = MaterialTheme.spacing.medium
                    )
            ) {
                onEvent.invoke(ThemeEvents.OnNavBarColorChanged(color))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun AddEditScreenPreview() {
    ABetterUTheme {
        AddEditScreen()
    }
}