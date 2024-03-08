package com.ajs.abetteru.ui.screens.journal.main.compose

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ajs.abetteru.R
import com.ajs.abetteru.navigation.JournalScreenRoute
import com.ajs.abetteru.ui.comman.JournalItem
import com.ajs.abetteru.ui.comman.MainContainer
import com.ajs.abetteru.ui.comman.TitleComponent
import com.ajs.abetteru.ui.comman.journalModel
import com.ajs.abetteru.ui.theme.ABetterUTheme
import com.ajs.abetteru.ui.theme.spacing

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JournalScreen(
    modifier: Modifier = Modifier,
    navController: NavController = rememberNavController(),
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val lazyColumnScrollState = rememberLazyListState()
    MainContainer(title = stringResource(id = R.string.Journal),
        modifier = modifier,
        scrollBehavior = scrollBehavior,
        floatingActionButton = {
            AnimatedVisibility(
                visible = !lazyColumnScrollState.isScrollInProgress,
                enter = slideInHorizontally {
                    it.div(2)

                } + fadeIn(),
                exit = slideOutHorizontally {
                    it.div(2)
                } + fadeOut()
            ) {
                ExtendedFloatingActionButton(onClick = {
                    navController.navigate(
                        JournalScreenRoute.AddEditScreen.route
                    )
                }) {
                    Icon(
                        imageVector = Icons.Outlined.Edit,
                        contentDescription = null,
                        modifier = Modifier.padding(end = MaterialTheme.spacing.small)
                    )
                    Text(text = stringResource(id = R.string.AddEntry))
                }
            }
        }) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .nestedScroll(scrollBehavior.nestedScrollConnection)
                .consumeWindowInsets(paddingValues)
                .padding(horizontal = MaterialTheme.spacing.medium),
            contentPadding = PaddingValues(bottom = MaterialTheme.spacing.bottomPadding),
            state = lazyColumnScrollState
        ) {
            item {
                SearchCard()
                Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))
                TitleComponent(
                    title = stringResource(id = R.string.RecentEntries)
                )
            }
            items(journalModel) {
                JournalItem(
                    model = it
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun JournalScreenPreview() {
    ABetterUTheme {
        JournalScreen()
    }
}