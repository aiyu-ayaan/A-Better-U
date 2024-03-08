package com.ajs.abetteru.ui.comman

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.ajs.abetteru.ui.theme.ABetterUTheme
import com.ajs.abetteru.ui.theme.spacing

@Composable
fun ImageItem(
    modifier: Modifier = Modifier,
    model: Any? = null,
    onDelete: () -> Unit = { },
    onImageClick: () -> Unit = { }
) {
    Box {
        AsyncImage(
            modifier = modifier
                .clip(
                    RoundedCornerShape(MaterialTheme.spacing.extraLarge)
                )
                .fillMaxWidth()
                .clickable { onImageClick() }
                .padding(
                    MaterialTheme.spacing.medium
                )
                .height(200.dp),
            model = model,
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        IconButton(
            onClick = onDelete,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(end = MaterialTheme.spacing.medium, top = MaterialTheme.spacing.medium),
            colors = IconButtonDefaults.iconButtonColors(
                containerColor = Color.White,
                contentColor = MaterialTheme.colorScheme.primary
            )
        ) {
            Icon(imageVector = Icons.Outlined.Close, contentDescription = "Delete")
        }
    }
}

@Preview
@Composable
fun ImageItemPreview() {
    ABetterUTheme {
        ImageItem()
    }
}
