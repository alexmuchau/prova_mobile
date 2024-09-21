package com.example.app.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun Title(text: String){
    Text(
        text=text.uppercase(),
        fontSize=50.sp,
        fontWeight = FontWeight.Bold,
        lineHeight = 50.sp
    )
}