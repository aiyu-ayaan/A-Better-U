package com.ajs.abetteru.ui.activities.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.ajs.abetteru.navigation.ABetterUNavigationGraph
import com.ajs.abetteru.ui.theme.ABetterUTheme
import com.ajs.abetteru.ui.theme.ThemeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val themeViewModel: ThemeViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            ABetterUTheme(
                systemTheme = themeViewModel.systemTheme.value
            ) {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                ) { innerPadding ->
                    ABetterUNavigationGraph(
                        navController = navController,
                        modifier = Modifier.windowInsetsPadding(
                            WindowInsets(
                                bottom = innerPadding.calculateBottomPadding(),
                            )
                        ),
                        onEvent = themeViewModel::onEvent
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(
    name: String, modifier: Modifier = Modifier,
    onCLick: () -> Unit = {}
) {
    Text(
        text = "Hello $name!",
        modifier = modifier
            .clickable {
                onCLick.invoke()
            }
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ABetterUTheme {
        Greeting("Android")
    }
}