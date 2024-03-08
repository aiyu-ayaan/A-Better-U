package com.ajs.abetteru.ui.comman

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ajs.abetteru.ui.theme.ABetterUTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Toolbar(
    modifier: Modifier = Modifier,
    title: String = "",
    onNavigationClick: (() -> Unit)? = null,
    actions: @Composable RowScope.() -> Unit = {},
    scrollBehavior: TopAppBarScrollBehavior? = null
) {
    val icon: @Composable () -> Unit = if (onNavigationClick != null) {
        {
            IconButton(onClick = onNavigationClick) {
                Icon(
                    imageVector = Icons.AutoMirrored.Default.ArrowBack, contentDescription = "Back"
                )
            }
        }
    } else {
        {}
    }

    TopAppBar(
        title = {
            Text(text = title)
        },
        modifier = modifier,
        actions = actions,
        scrollBehavior = scrollBehavior,
        navigationIcon = icon
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainContainer(
    modifier: Modifier = Modifier,
    title: String = "",
    scrollBehavior: TopAppBarScrollBehavior? = null,
    onNavigationClick: (() -> Unit)? = null,
    floatingActionButton: @Composable () -> Unit = {},
    actions: @Composable RowScope.() -> Unit = {},
    content: @Composable (PaddingValues) -> Unit = { }
) {

    Scaffold(
        modifier = modifier, topBar = {
            Toolbar(
                title = title,
                scrollBehavior = scrollBehavior,
                onNavigationClick = onNavigationClick,
                actions = actions
            )
        }, floatingActionButton = floatingActionButton
    ) {
        content(it)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
private fun MainContainerPreview() {
    ABetterUTheme {
        MainContainer(

        )
    }
}