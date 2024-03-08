package com.ajs.abetteru.ui.screens.journal.main

import androidx.lifecycle.ViewModel
import com.ajs.core.use_cases.JournalUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class JournalViewModel @Inject constructor(
    private val case : JournalUseCases
) : ViewModel() {
    val journals = case.getAllJournalUseCase.invoke()
}