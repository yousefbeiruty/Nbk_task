package com.example.nbktask.compose.ui.theme

import androidx.annotation.ColorRes
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xC653C6B7)
val PurpleGrey80 = Color(0xE653C6B7)
val Pink80 = Color(0xFFEFB8C8)
val Purpple=Color(530760)
val Purple40 = Color(0xFF53C6B7)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)
val Gray=Color(0xFFEFEDF2)
val texColor=Color(0xFFB4B4B6)


data class MyColors(
    val white: Color = Color.White,
    val Purple80:Color=Color(0xFF53C6B7),
    val Purple40 :Color= Color(0xFF53C6B7),
    val Purpple :Color=Color(0xE653C6B7),
    val Black:Color=Color(0xFF181718),
    val Yellow:Color= Color(0xFFFFEB3B),
    val Gray:Color = Color(0xFFDBDBDA),
    val texColor:Color= Color(0xFFB4B4B6)
)
val LocalColors= compositionLocalOf { MyColors() }
val MaterialTheme.myColors: MyColors
    @Composable
    @ReadOnlyComposable
    get() = LocalColors.current
