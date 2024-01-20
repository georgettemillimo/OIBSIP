package com.devmillimo.calculatorapp.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.devmillimo.calculatorapp.ViewModels.LocalTheme
import com.devmillimo.calculatorapp.ViewModels.ThemeViewModel

private val DarkColorPalette = darkColors(
    primary = Black200,
    secondary = Black500,
    background = Black500
)

private val LightColorPalette = lightColors(
    primary = Color.White,
    secondary = DarkWhite,
    background = Black200

)

@Composable
fun CalculatorAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    //dynamicColor: Boolean =true,
    content: @Composable () -> Unit
) {


    /*val  view = LocalView.current
    if(!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colors.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }*/


    val  viewModel = ThemeViewModel(initialDarkMode = darkTheme)
    val darkModeEnabled = viewModel.darkmode.collectAsState()
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }
    /*val colors = when{
        dynamicColor && Build.VERSION.SDK_INT ≥ Build.VERSION_CODES.S ->{
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColor(context) else dynamicLightColor(context)
        }
        darkTheme -> DarkColorPalette
        else  -> LightColorPalette
    }*/


    CompositionLocalProvider(LocalTheme provides viewModel) {
        MaterialTheme(
            colors = colors,
            typography = Typography,
            shapes = Shapes,
            content = content
        )
        
    }
}