package com.ajs.abetteru.ui.screens.journal.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ajs.abetteru.R
import com.ajs.abetteru.ui.theme.ABetterUTheme
import com.ajs.abetteru.ui.theme.spacing

@Composable
fun SearchCard(
    modifier: Modifier = Modifier, onClick: () -> Unit = {}
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                vertical = MaterialTheme.spacing.small
            )
            .height(56.dp),
        shape = CircleShape,
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .clickable {
                    onClick.invoke()
                },
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = stringResource(R.string.search_for_a_journal_entry),
                modifier = Modifier
                    .padding(MaterialTheme.spacing.small)
                    .padding(start = MaterialTheme.spacing.large),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SearchCardPreview() {
    ABetterUTheme {
        SearchCard()
    }
}