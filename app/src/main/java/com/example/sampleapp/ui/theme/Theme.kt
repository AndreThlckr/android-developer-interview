package com.example.sampleapp.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.ViewCompat

private val DarkColorScheme = darkColors(
    primary = DarkBlue,
    secondary = DarkOrange
)

private val LightColorScheme = lightColors(
    primary = Blue,
    secondary = Orange
)

private val LocalDimens = staticCompositionLocalOf { DefaultDimens }

@Composable
fun ProvideDimens(
    dimens: Dimensions,
    content: @Composable () -> Unit
) {
    val dimensionSet = remember { dimens }
    CompositionLocalProvider(LocalDimens provides dimensionSet, content = content)
}

@Composable
fun SampleAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colors = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            if (darkTheme) DarkColorScheme else LightColorScheme
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            (view.context as Activity).window.statusBarColor = colors.primary.toArgb()
            ViewCompat.getWindowInsetsController(view)?.isAppearanceLightStatusBars = darkTheme
        }
    }

    ProvideDimens(dimens = DefaultDimens) {
        MaterialTheme(
            colors = colors,
            typography = Typography,
            content = content
        )
    }
}

object SampleAppTheme {
    val dimens: Dimensions
        @Composable
        get() = LocalDimens.current

    val typography: androidx.compose.material.Typography
        @Composable
        get() = MaterialTheme.typography
}

val Dimens: Dimensions
    @Composable
    get() = SampleAppTheme.dimens
