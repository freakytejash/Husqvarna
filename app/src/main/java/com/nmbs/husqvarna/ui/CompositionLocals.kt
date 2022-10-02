package com.nmbs.husqvarna.ui

import androidx.compose.runtime.staticCompositionLocalOf
import com.nmbs.husqvarna.utils.DateFormatter
import com.nmbs.husqvarna.utils.TimeFormatter

val LocalDateFormatter = staticCompositionLocalOf<DateFormatter> {
    error("DateFormatter not provided")
}

val LocalTimeFormatter = staticCompositionLocalOf<TimeFormatter> {
    error("TimeFormatter not provided")
}