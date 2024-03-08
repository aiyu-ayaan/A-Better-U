package com.ajs.abetteru.ui.comman

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ajs.abetteru.ui.theme.ABetterUTheme
import com.ajs.abetteru.ui.theme.spacing
import com.ajs.core.database.journal.JournalModel
import com.ajs.core.database.journal.colorList
import com.ajs.core.utils.toBitmap

@Composable
fun JournalItem(
    modifier: Modifier = Modifier,
    model: JournalModel
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = MaterialTheme.spacing.small),
        colors = CardDefaults.cardColors(
            containerColor = Color(model.color)
        )
    ) {
        Row(
            modifier = Modifier
                .padding(
                    MaterialTheme.spacing.large
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Column(
                modifier = Modifier
                    .weight(
                        if (model.imageData != null) .8f else 1f
                    )
            ) {
                Text(
                    text = model.title,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(
                    modifier =
                    Modifier.padding(
                        vertical = MaterialTheme.spacing.small
                    )
                )
                Text(
                    text = model.description,
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
            }
            if (model.imageData != null)
                Image(
                    modifier = Modifier
                        .heightIn(
                            min = 40.dp,
                        )
                        .widthIn(
                            max = 40.dp
                        )
                        .weight(.2f)
                        .aspectRatio(
                            1f
                        ),
                    bitmap = model.imageData!!.toBitmap().asImageBitmap(),
                    contentDescription = "Image"
                )
        }
    }
}

val journalModel = listOf(
    JournalModel(
        title = "Which habits do you want to develop this year Which habits do you want to develop this year ?",
        description = "Which habits do you want to develop this yearWhich habits do you want to develop this yearWhich habits do you want to develop this yearWhich habits do you want to develop this year",
    ),
    JournalModel(
        title = "Which habits do you want to develop this year Which habits do you want to develop this year ?",
        description = "Which habits do you want to develop this yearWhich habits do you want to develop this yearWhich habits do you want to develop this yearWhich habits do you want to develop this year",
    ),
    JournalModel(
        title = "Which habits do you want to develop this year Which habits do you want to develop this year ?",
        description = "Which habits do you want to develop this yearWhich habits do you want to develop this yearWhich habits do you want to develop this yearWhich habits do you want to develop this year",
    ),
    JournalModel(
        title = "Which habits do you want to develop this year Which habits do you want to develop this year ?",
        description = "Which habits do you want to develop this yearWhich habits do you want to develop this yearWhich habits do you want to develop this yearWhich habits do you want to develop this year",
    ),
    JournalModel(
        title = "Which habits do you want to develop this year Which habits do you want to develop this year ?",
        description = "Which habits do you want to develop this yearWhich habits do you want to develop this yearWhich habits do you want to develop this yearWhich habits do you want to develop this year",
    ),
    JournalModel(
        title = "Which habits do you want to develop this year Which habits do you want to develop this year ?",
        description = "Which habits do you want to develop this yearWhich habits do you want to develop this yearWhich habits do you want to develop this yearWhich habits do you want to develop this year",
    ),
    JournalModel(
        title = "Which habits do you want to develop this year Which habits do you want to develop this year ?",
        description = "Which habits do you want to develop this yearWhich habits do you want to develop this yearWhich habits do you want to develop this yearWhich habits do you want to develop this year",
    ),
    JournalModel(
        title = "Which habits do you want to develop this year Which habits do you want to develop this year ?",
        description = "Which habits do you want to develop this yearWhich habits do you want to develop this yearWhich habits do you want to develop this yearWhich habits do you want to develop this year",
    ),
    JournalModel(
        title = "Which habits do you want to develop this year Which habits do you want to develop this year ?",
        description = "Which habits do you want to develop this yearWhich habits do you want to develop this yearWhich habits do you want to develop this yearWhich habits do you want to develop this year",
    ),
    JournalModel(
        title = "Which habits do you want to develop this year Which habits do you want to develop this year ?",
        description = "Which habits do you want to develop this yearWhich habits do you want to develop this yearWhich habits do you want to develop this yearWhich habits do you want to develop this year",
    ),
)

@Preview(showBackground = true)
@Composable
private fun JournalItemPreview() {
    ABetterUTheme {
        JournalItem(
            model = JournalModel(
                color = colorList[1],
                title = "Which habits do you want to develop this year Which habits do you want to develop this year ?",
                description = "des",
            )
        )
    }
}