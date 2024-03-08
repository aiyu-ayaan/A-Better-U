package com.ajs.abetteru.ui.comman

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SheetState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ajs.abetteru.ui.theme.ABetterUTheme
import com.ajs.abetteru.ui.theme.spacing
import com.ajs.core.database.journal.getColorList


@Composable
internal fun ColorItems(
    modifier: Modifier = Modifier,
    color: Color = Color.Red,
    isSelected: Boolean = false,
    onColorClick: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .size(50.dp)
            .background(color = color, shape = CircleShape)
            .clickable {
                onColorClick.invoke()
            }
            .let {
                if (isSelected) it.border(1.dp, Color.Black, CircleShape) else it
            },
        contentAlignment = Alignment.Center
    ) {
        if (isSelected) {
            Box(
                modifier = Modifier
                    .size(20.dp)
                    .background(Color.Black, CircleShape)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ColorBottomSheet(
    modifier: Modifier = Modifier,
    selected: Color,
    onColorClick: (Color) -> Unit = {},
    sheetState: SheetState = rememberModalBottomSheetState(),
    onDismiss: () -> Unit = {}
) {
    UniversalBottomSheet(
        modifier = modifier,
        fullScreen = false,
        onDismissRequest = onDismiss,
        containerColor = Color.White,
        sheetState = sheetState
    ) {
        LazyRow(
            modifier = Modifier.padding(
                MaterialTheme.spacing.medium
            ),
            contentPadding = PaddingValues(
                start = MaterialTheme.spacing.medium,
                end = MaterialTheme.spacing.medium,
            ),
            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium)
        ) {
            items(getColorList().map { Color(it) }) {
                ColorItems(
                    color = it,
                    isSelected = selected == it,
                    onColorClick = { onColorClick(it) }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
private fun BottomSheetDefaultsPreview() {
    ABetterUTheme {
        ColorBottomSheet(
            selected = Color(getColorList()[0])
        )
    }
}