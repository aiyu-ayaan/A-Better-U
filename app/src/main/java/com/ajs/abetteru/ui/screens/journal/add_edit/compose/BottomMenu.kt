package com.ajs.abetteru.ui.screens.journal.add_edit.compose

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AddPhotoAlternate
import androidx.compose.material.icons.outlined.ColorLens
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ajs.abetteru.ui.theme.ABetterUTheme
import com.ajs.abetteru.ui.theme.spacing

@Composable
internal fun BottomMenu(
    modifier: Modifier = Modifier,
    onImageClick: () -> Unit = {},
    onColorClick: () -> Unit = {}
) {
    Card(
        shape = CircleShape,
        modifier = modifier
            .padding(
                horizontal = MaterialTheme.spacing.medium,
                vertical = MaterialTheme.spacing.large
            ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer

        )
    ) {
        Row(
            modifier = Modifier
                .padding(
                    horizontal = MaterialTheme.spacing.medium,
                )
        ) {
            IconButton(onClick =onImageClick) {
                Icon(
                    imageVector = Icons.Outlined.AddPhotoAlternate,
                    contentDescription = "Pick Image"
                )
            }
            Spacer(modifier = Modifier.width(MaterialTheme.spacing.medium))
            IconButton(onClick = onColorClick) {
                Icon(
                    imageVector = Icons.Outlined.ColorLens,
                    contentDescription = "Pick Image"
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun BottomMenuPreview() {
    ABetterUTheme {
        BottomMenu()
    }
}