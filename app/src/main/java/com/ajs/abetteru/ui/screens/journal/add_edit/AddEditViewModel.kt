package com.ajs.abetteru.ui.screens.journal.add_edit

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.ajs.abetteru.utils.questions
import com.ajs.core.database.journal.getColorList
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddEditViewModel @Inject constructor(

) : ViewModel() {
    private val _color = mutableStateOf(Color(getColorList().random()))
    val color: State<Color> get() = _color
    private val _question = mutableStateOf(questions.random())
    val question: State<String> get() = _question

    private val _answer = mutableStateOf("")
    val answer: State<String> get() = _answer

    fun onEvent(event: AddEditEvents) {
        when (event) {
            is AddEditEvents.OnQuestionChanged -> {
                _question.value = questions.random()
            }

            is AddEditEvents.OnAnswerChanged -> {
                _answer.value = event.answer
            }

            is AddEditEvents.OnColorChanged ->
                _color.value = event.color
        }
    }

}
