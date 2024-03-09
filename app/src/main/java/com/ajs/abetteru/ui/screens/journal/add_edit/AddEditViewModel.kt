package com.ajs.abetteru.ui.screens.journal.add_edit

import android.content.Context
import android.graphics.Bitmap
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ajs.abetteru.utils.convertUriToBitmap
import com.ajs.abetteru.utils.questions
import com.ajs.abetteru.utils.toBitmap
import com.ajs.abetteru.utils.toBytesArray
import com.ajs.core.database.journal.JournalModel
import com.ajs.core.database.journal.getColorList
import com.ajs.core.use_cases.JournalUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val journalUseCases: JournalUseCases,
    state: SavedStateHandle
) : ViewModel() {
    val id = state.get<Int>("modelId") ?: -1
    private var _created: Long? = null

    private val _color = mutableStateOf(Color(getColorList().random()))
    val color: State<Color> get() = _color
    private val _question = mutableStateOf(questions.random())
    val question: State<String> get() = _question

    private val _answer = mutableStateOf("")
    val answer: State<String> get() = _answer

    private val _bitmap = mutableStateOf<Bitmap?>(null)
    val bitmap: State<Bitmap?> get() = _bitmap

    init {
        getElementById()
    }


    private fun getElementById() {
        if (id != -1) {
            viewModelScope.launch {
                journalUseCases.getJournalByIdUseCase.invoke(id)?.let { model ->
                    _question.value = model.question
                    _answer.value = model.answer
                    _color.value = Color(model.color)
                    _bitmap.value = model.imageData?.toBitmap()
                    _created = model.created
                }
            }
        }
    }

    fun onEvent(event: AddEditEvents) {
        when (event) {
            is AddEditEvents.OnQuestionChanged -> {
                _question.value = questions.random()
            }

            is AddEditEvents.OnAnswerChanged -> {
                _answer.value = event.answer
            }

            is AddEditEvents.OnColorChanged -> _color.value = event.color

            is AddEditEvents.Save -> viewModelScope.launch {
                journalUseCases.insertJournalUseCase.invoke(
                    JournalModel(
                        question = question.value,
                        answer = answer.value,
                        color = color.value.toArgb(),
                        imageData = bitmap.value?.toBytesArray(),
                        created = _created ?: System.currentTimeMillis()
                    ).let {
                        if (id != -1) it.copy(id = id) else it
                    }
                )
            }

            is AddEditEvents.OnImagePicked -> {
                if (event.imageUri == null) {
                    _bitmap.value = null
                    return
                }
                _bitmap.value = convertUriToBitmap(
                    context = context, imageUri = event.imageUri
                )
            }

            AddEditEvents.OnDelete -> {
                viewModelScope.launch {
                    journalUseCases.deleteJournalByIdUseCase.invoke(id)
                }
            }
        }
    }

}
