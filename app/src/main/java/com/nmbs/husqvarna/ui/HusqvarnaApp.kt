package com.nmbs.husqvarna.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.nmbs.husqvarna.theme.DemoTheme
import com.nmbs.husqvarna.utils.DateFormatter
import com.nmbs.husqvarna.utils.TimeFormatter

@Composable
fun HusqvarnaApp() {
    val useDarkTheme = isSystemInDarkTheme()

    AppFoundation(useDarkTheme = useDarkTheme) {
        val systemUiController = rememberSystemUiController()
        //make sure the system bars match the current theme
        SideEffect {
            systemUiController.setSystemBarsColor(Color.Transparent, darkIcons = !useDarkTheme)
        }

        Row(
            Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surface)
                .statusBarsPadding()
                .windowInsetsPadding(
                    WindowInsets
                        .navigationBars
                        .only(WindowInsetsSides.Horizontal + WindowInsetsSides.Top)
                )
        ) {
        }
    }

}

@Composable
fun AppFoundation(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable() () -> Unit
) {
    CompositionLocalProvider(
        LocalDateFormatter provides DateFormatter,
        LocalTimeFormatter provides TimeFormatter
    ) {
        DemoTheme(useDarkTheme = useDarkTheme, content = content)
    }
}
