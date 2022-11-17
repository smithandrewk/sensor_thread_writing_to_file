package com.example.separate_thread_hello_world.presentation.theme

import androidx.compose.runtime.Composable
import androidx.wear.compose.material.MaterialTheme

@Composable
fun Separate_thread_hello_worldTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colors = wearColorPalette,
        typography = Typography,
        // For shapes, we generally recommend using the default Material Wear shapes which are
        // optimized for round and non-round devices.
        content = content
    )
}