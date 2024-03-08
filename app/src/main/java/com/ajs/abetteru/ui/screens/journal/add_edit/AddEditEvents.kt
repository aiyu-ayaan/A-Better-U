package com.ajs.abetteru.ui.screens.journal.add_edit

import android.net.Uri
import androidx.compose.ui.graphics.Color

sealed interface AddEditEvents {
    data object OnQuestionChanged : AddEditEvents
    data class OnColorChanged(val color: Color) : AddEditEvents
    data class OnAnswerChanged(val answer: String) : AddEditEvents
    data class Save(val imageUri: Uri?) : AddEditEvents
}