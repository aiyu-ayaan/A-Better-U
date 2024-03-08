package com.ajs.abetteru.ui.theme

import android.app.Activity
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat


internal val colorScheme = ColorScheme(
    background = Color(0xfffff8f7),
    error = Color(0xffba1a1a),
    errorContainer = Color(0xffffdad6),
    inverseOnSurface = Color(0xfff9eeef),
    inversePrimary = Color(0xffffb1c1),
    inverseSurface = Color(0xff352f30),
    onBackground = Color(0xff1f1a1b),
    onError = Color(0xffffffff),
    onErrorContainer = Color(0xff410002),
    onPrimary = Color(0xffffffff),
    onPrimaryContainer = Color(0xff3d0317),
    onSecondary = Color(0xffffffff),
    onSecondaryContainer = Color(0xff29161a),
    onSurface = Color(0xff1f1a1b),
    onSurfaceVariant = Color(0xff4c4545),
    onTertiary = Color(0xffffffff),
    onTertiaryContainer = Color(0xff2b1701),
    outline = Color(0xff7d7575),
    outlineVariant = Color(0xffcbbcbd),
    primary = Color(0xff924659),
    primaryContainer = Color(0xffffd9df),
    scrim = Color(0xff000000),
    secondary = Color(0xff72585c),
    secondaryContainer = Color(0xfffddadf),
    surface = Color(0xfffff8f7),
    surfaceTint = Color(0xff924659),
    surfaceVariant = Color(0xffebe0e0),
    tertiary = Color(0xff76593a),
    tertiaryContainer = Color(0xffffdcbd),
    surfaceBright = Color(0xfffff8f7),
    surfaceDim = Color(0xffe2d8d8),
    surfaceContainer = Color(0xfff6ebec),
    surfaceContainerHigh = Color(0xfff1e6e6),
    surfaceContainerHighest = Color(0xffebe0e0),
    surfaceContainerLow = Color(0xfffcf1f1),
    surfaceContainerLowest = Color(0xffffffff),
)

@Immutable
data class SystemTheme(
    val colorScheme: ColorScheme,
    val navBarColor: Color = colorScheme.surface,
)

val LocalTheme = compositionLocalOf { SystemTheme(colorScheme) }


@Composable
fun ABetterUTheme(
    systemTheme: SystemTheme = SystemTheme(
        colorScheme = colorScheme
    ),
    content: @Composable () -> Unit,
) {
    CompositionLocalProvider(
        LocalTheme provides systemTheme,
        LocalSpacing provides Spacing()
    ) {
        MaterialTheme(
            colorScheme = LocalTheme.current.colorScheme,
            typography = Typography,
            content = content
        )
        val view = LocalView.current
        val color = LocalTheme.current.navBarColor.toArgb()
        if (!view.isInEditMode) {
            SideEffect {
                val window = (view.context as Activity).window
                window.navigationBarColor = color
                WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = true
                WindowCompat.getInsetsController(window, view).isAppearanceLightNavigationBars =
                    true
            }
        }
    }
}