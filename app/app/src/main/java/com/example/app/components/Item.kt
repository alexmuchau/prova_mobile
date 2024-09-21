package com.example.app.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.app.classes.Produto
import com.google.gson.Gson

@Composable
fun Item(number: Int, produto: Produto, navController: NavController){
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(32.dp))
            .background(color = Color.LightGray)
            .padding(20.dp),
        onClick = {
            val produtoJson = Gson().toJson(produto)

            navController.navigate("produto/$produtoJson")
        }
    ) {
        Text(
            text="$number - ${produto.nome}",
            color = Color.Black,
            fontSize = 22.sp,
            modifier = Modifier.background(Color.Transparent)
        )
    }
}