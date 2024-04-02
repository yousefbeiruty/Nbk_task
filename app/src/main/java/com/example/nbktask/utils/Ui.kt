package com.example.nbktask.utils


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun EmptyList(message:String) {
    Box(  modifier = Modifier.fillMaxSize(),
        contentAlignment = androidx.compose.ui.Alignment.Center){
        Text(
            text = message,
            modifier = Modifier.padding(16.dp),
            color = Color.Black
        )
    }
}