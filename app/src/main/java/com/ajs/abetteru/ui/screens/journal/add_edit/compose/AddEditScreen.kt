package com.ajs.abetteru.ui.screens.journal.add_edit.compose

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ajs.abetteru.R
import com.ajs.abetteru.ui.comman.ColorBottomSheet
import com.ajs.abetteru.ui.comman.EditText
import com.ajs.abetteru.ui.comman.MainContainer
import com.ajs.abetteru.ui.comman.TitleItem
import com.ajs.abetteru.ui.comman.UniversalBottomSheet
import com.ajs.abetteru.ui.screens.journal.add_edit.AddEditEvents
import com.ajs.abetteru.ui.theme.ABetterUTheme
import com.ajs.abetteru.ui.theme.ThemeEvents
import com.ajs.abetteru.ui.theme.spacing
import com.ajs.abetteru.utils.questions

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
    val questionBottomSheet = rememberModalBottomSheetState()
    val colorBottomSheet = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showQuestionBottomSheet by remember { mutableStateOf(false) }
    var showColorBottomSheet by remember { mutableStateOf(false) }
    onThemeEvents.invoke(ThemeEvents.OnNavBarColorChanged(color))
    val animatedColor by animateColorAsState(targetValue = color, label = "color")
    MainContainer(modifier = modifier, appBarColor = color, onNavigationClick = {
        navController.popBackStack()
    }, actions = {
        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                imageVector = Icons.Outlined.Edit, contentDescription = "Edit"
            )
        }
        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                imageVector = Icons.Outlined.Share, contentDescription = "Edit"
            )
        }
        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                imageVector = Icons.Outlined.Delete, contentDescription = "Edit"
            )
        }
    }) {
        Box(modifier = Modifier
            .imePadding()
            .fillMaxSize()
            .padding(it)
            .drawBehind {
                drawRect(animatedColor)
            }) {
            Column(
                modifier = Modifier.padding(
                        horizontal = MaterialTheme.spacing.small
                    ),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
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
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
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

                    TextButton(
                        modifier = Modifier.padding(
                            end = MaterialTheme.spacing.medium
                        ),
                        onClick = { showQuestionBottomSheet = true },
                    ) {
                        Text(text = "Show All")
                    }
                }
                EditText(
                    label = "Answer",
                    modifier = Modifier
                        .fillMaxSize()
                        .animateContentSize(),
                    value = answer,
                    onValueChange = { typedText ->
                        onEvent.invoke(AddEditEvents.OnAnswerChanged(typedText))
                    },
                    textStyle = MaterialTheme.typography.bodyLarge,
                )
            }
            BottomMenu(modifier = Modifier.align(Alignment.BottomStart), onColorClick = {
                showColorBottomSheet = true
            })
        }

//        BottomSheet
        if (showQuestionBottomSheet) {
            UniversalBottomSheet(sheetState = questionBottomSheet, onDismissRequest = {
                showQuestionBottomSheet = false
            }) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = MaterialTheme.spacing.medium
                        ),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "Choose a question")
                    LazyColumn {
                        items(questions) { question ->
                            TitleItem(
                                text = question
                            )
                        }
                    }
                }

            }
        }
        if (showColorBottomSheet) {
            ColorBottomSheet(sheetState = colorBottomSheet,
                modifier = Modifier,
                selected = color,
                onColorClick = { color ->
                    onThemeEvents.invoke(ThemeEvents.OnNavBarColorChanged(color))
                    onEvent.invoke(AddEditEvents.OnColorChanged(color))
                },
                onDismiss = {
                    showColorBottomSheet = false
                })
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