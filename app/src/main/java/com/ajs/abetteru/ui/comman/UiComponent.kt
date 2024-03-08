package com.ajs.abetteru.ui.comman

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ajs.abetteru.ui.theme.ABetterUTheme
import com.ajs.abetteru.ui.theme.spacing

@Composable
fun TitleComponent(
    title: String = "ABetterU"
) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                MaterialTheme.spacing.medium
            ),
        text = title,
        style = MaterialTheme.typography.bodyLarge,
        color = MaterialTheme.colorScheme.onSurface.copy(
            alpha = 0.87f
        )
    )
}

@Preview(showBackground = true)
@Composable
private fun TitleComponentPreview() {
    ABetterUTheme {
        TitleComponent()
    }
}