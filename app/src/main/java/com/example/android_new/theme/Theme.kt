package com.example.android_new.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = Red,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

private val LightColorScheme = lightColorScheme(
    primary = Red,
    secondary = PurpleGrey40,
    tertiary = Pink40

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

// 应用主题
@Composable
fun ApprenticeshipTheme(
    darkTheme: Boolean = isSystemInDarkTheme(), // 是否使用暗色主题，默认为系统主题
    dynamicColor: Boolean = false, // 是否使用动态颜色，默认为 true
    content: @Composable () -> Unit // 内容函数
) {
    val colorScheme = when {
        dynamicColor -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme   // 如果使用了暗色主题，则使用暗色颜色方案
        else -> LightColorScheme       // 否则使用亮色颜色方案
    }

    val view = LocalView.current // 获取当前视图
    if (!view.isInEditMode) { // 如果不是在编辑模式下
        SideEffect { // 通过SideEffect注解修饰的函数，表示该函数是一个副作用函数
            val window = (view.context as Activity).window // 获取当前视图的上下文并转换为Activity类型，再获取其window对象
            window.statusBarColor = colorScheme.primary.toArgb() // 设置window的statusBarColor为colorScheme的primary颜色的ARGB值
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme // 获取window的InsetsController对象，并设置其isAppearanceLightStatusBars属性为darkTheme
        }
    }


    MaterialTheme(
        colorScheme = colorScheme, // 颜色方案
        typography = Typography, // 字体排版
        content = content // 内容函数
    )
}
