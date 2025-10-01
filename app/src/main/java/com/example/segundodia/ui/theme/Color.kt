package com.example.segundodia.ui.theme

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)

val col: Brush = Brush.verticalGradient(
    colors = listOf(
        Color.Red,
        Color.Black,
        Color.hsl(18f,1.0f, 0.489f,1f)
    )
)
val col2: Brush = Brush.verticalGradient(
    colors = listOf(
        Color.Magenta,
        Color.Black,
        Color.hsl(278f, 1.0f, 0.354f,1f)
    )
)
val col3: Brush = Brush.verticalGradient(
    colors = listOf(
        Color.Red,
        Color.Black,
        Color.hsl(278f, 1.0f, 0.354f,1f)
    )
)