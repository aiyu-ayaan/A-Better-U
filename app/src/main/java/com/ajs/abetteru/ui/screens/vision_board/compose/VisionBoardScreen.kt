package com.ajs.abetteru.ui.screens.vision_board.compose

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ajs.abetteru.R
import com.ajs.abetteru.ui.comman.MainContainer
import com.ajs.abetteru.ui.theme.ABetterUTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VisionBoardScreen(
    modifier: Modifier = Modifier,
    navController: NavController = rememberNavController()
) {
    MainContainer(
        modifier = modifier,
        title = stringResource(id = R.string.VisionBoard)
    )
}

@Preview(showBackground = true)
@Composable
private fun VisionBoardScreenPreview() {
    ABetterUTheme {
        VisionBoardScreen()
    }
}