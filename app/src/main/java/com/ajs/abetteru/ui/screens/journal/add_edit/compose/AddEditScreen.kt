package com.ajs.abetteru.ui.screens.journal.add_edit.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ajs.abetteru.ui.comman.MainContainer
import com.ajs.abetteru.ui.theme.ABetterUTheme
import com.ajs.abetteru.ui.theme.ThemeEvents
import com.ajs.core.database.journal.colorList

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditScreen(
    modifier: Modifier = Modifier,
    onEvent: (ThemeEvents) -> Unit = {},
    navController: NavController = rememberNavController(),
) {
    val color = colorList[0]
    MainContainer(
        modifier = modifier,
        appBarColor = Color(color),
        onNavigationClick = {
            navController.popBackStack()
        },
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .background(color = Color(color))
        ) {
            onEvent.invoke(ThemeEvents.OnNavBarColorChanged(Color(color)))
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