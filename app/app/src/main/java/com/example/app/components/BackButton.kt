package com.example.app.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun BackButton(navController: NavController, label: String) {
    Row(
        modifier = Modifier
            .padding(4.dp)
            .clickable { navController.popBackStack() }
    ) {
        Text(text = label, modifier = Modifier.padding(start = 8.dp))
    }
}