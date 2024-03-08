package com.ajs.abetteru.ui.screens.journal.compose

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import com.ajs.abetteru.R
import com.ajs.abetteru.ui.comman.MainContainer
import com.ajs.abetteru.ui.theme.ABetterUTheme
import com.ajs.abetteru.ui.theme.spacing

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JournalScreen(
    modifier: Modifier = Modifier
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    MainContainer(title = stringResource(id = R.string.Journal),
        modifier = modifier,
        scrollBehavior = scrollBehavior,
        floatingActionButton = {
            ExtendedFloatingActionButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Outlined.Edit,
                    contentDescription = null,
                    modifier = Modifier.padding(end = MaterialTheme.spacing.small)
                )
                Text(text = stringResource(id = R.string.AddEntry))
            }
        }) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .nestedScroll(scrollBehavior.nestedScrollConnection)
                .consumeWindowInsets(paddingValues)
                .padding(horizontal = MaterialTheme.spacing.medium),
            contentPadding = PaddingValues(bottom = MaterialTheme.spacing.bottomPadding)
        ) {
            item {
                SearchCard()
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