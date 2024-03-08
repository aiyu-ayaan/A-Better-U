package com.ajs.abetteru.ui.screens.journal.add_edit.compose

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Redeem
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ajs.abetteru.R
import com.ajs.abetteru.ui.comman.EditText
import com.ajs.abetteru.ui.comman.MainContainer
import com.ajs.abetteru.ui.screens.journal.add_edit.AddEditEvents
import com.ajs.abetteru.ui.theme.ABetterUTheme
import com.ajs.abetteru.ui.theme.ThemeEvents
import com.ajs.abetteru.ui.theme.spacing

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditScreen(
    modifier: Modifier = Modifier,
    question: String = "What's on your mind?",
    answer: String = "",
    onThemeEvents: (ThemeEvents) -> Unit = {},
    color: Color = Color.Unspecified,
    navController: NavController = rememberNavController(),
    onEvent: (AddEditEvents) -> Unit = {}
) {
    onThemeEvents.invoke(ThemeEvents.OnNavBarColorChanged(color))
    MainContainer(
        modifier = modifier,
        appBarColor = color,
        onNavigationClick = {
            navController.popBackStack()
        },
        actions = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Outlined.Edit,
                    contentDescription = "Edit"
                )
            }
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Outlined.Share,
                    contentDescription = "Edit"
                )
            }
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Outlined.Delete,
                    contentDescription = "Edit"
                )
            }
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .background(color = color)
        ) {
            Column(
                modifier = Modifier
                    .padding(
                        horizontal = MaterialTheme.spacing.small
                    ),
                verticalArrangement = androidx.compose.foundation.layout.Arrangement.Center,
                horizontalAlignment = androidx.compose.ui.Alignment.Start
            ) {
                EditText(
                    modifier = Modifier
                        .fillMaxWidth()
                        .animateContentSize(),
                    value = question,
                    onValueChange = {},
                    textStyle = MaterialTheme.typography.titleLarge,
                    readOnly = true
                )
                Button(
                    onClick = { onEvent.invoke(AddEditEvents.OnQuestionChanged) },
                    modifier = Modifier.padding(
                        start = MaterialTheme.spacing.medium
                    )
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Redeem,
                        contentDescription = stringResource(R.string.choose_another_question)
                    )
                    Text(text = stringResource(R.string.choose_another_question))
                }
                EditText(
                    label = "Answer",
                    modifier = Modifier
                        .fillMaxWidth()
                        .animateContentSize(),
                    value = answer,
                    onValueChange = { typedText ->
                        onEvent.invoke(AddEditEvents.OnAnswerChanged(typedText))
                    },
                    textStyle = MaterialTheme.typography.bodyLarge,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun AddEditScreenPreview() {
    ABetterUTheme {
        AddEditScreen()
    }
}