package com.example.nbktask.compose.ui.theme


import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

data class MyTypography(
    val bigText: TextStyle = TextStyle(
        fontFamily = FontFamily.Monospace,
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold
    ),
    val smallText: TextStyle = TextStyle(
        fontFamily = FontFamily.Monospace,
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold
    )
)

val LocalTypography= compositionLocalOf { MyTypography() }

val MaterialTheme.myTypography: MyTypography
    @Composable
    @ReadOnlyComposable
    get() = LocalTypography.current
