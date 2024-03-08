package com.ajs.abetteru.ui.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ThemeViewModel @Inject constructor() : ViewModel() {
    private val _systemTheme = mutableStateOf(
        SystemTheme(
            colorScheme = colorScheme,
            navBarColor = colorScheme.surface
        )
    )

    val systemTheme: State<SystemTheme> get() = _systemTheme

    fun onEvent(
        event: ThemeEvents
    ) {
        when (event) {
            is ThemeEvents.OnColorSchemeChanged ->
                _systemTheme.value = _systemTheme.value.copy(
                    colorScheme = event.colorScheme
                )


            is ThemeEvents.OnNavBarColorChanged -> {
                _systemTheme.value = _systemTheme.value.copy(
                    navBarColor = event.color
                )
            }

            is ThemeEvents.SetDefaultColorScheme -> {
                _systemTheme.value = _systemTheme.value.copy(
                    colorScheme = colorScheme,
                    navBarColor = colorScheme.surface
                )
            }
        }
    }
}

sealed interface ThemeEvents {
    data class OnNavBarColorChanged(val color: Color) : ThemeEvents
    data class OnColorSchemeChanged(val colorScheme: ColorScheme) : ThemeEvents

    data object SetDefaultColorScheme : ThemeEvents
}