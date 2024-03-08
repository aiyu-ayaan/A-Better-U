package com.ajs.abetteru.ui.screens.journal.add_edit

sealed interface AddEditEvents {
    data object OnQuestionChanged : AddEditEvents

    data class OnAnswerChanged(val answer: String) : AddEditEvents

}