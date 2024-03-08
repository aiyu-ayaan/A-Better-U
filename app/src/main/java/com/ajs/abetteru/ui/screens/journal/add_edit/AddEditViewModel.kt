package com.ajs.abetteru.ui.screens.journal.add_edit

import androidx.lifecycle.ViewModel
import com.ajs.core.database.journal.colorList
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddEditViewModel @Inject constructor(

) : ViewModel() {
    val color = colorList.random()
}