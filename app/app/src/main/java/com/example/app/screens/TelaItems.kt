package com.example.app.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.app.components.BackButton
import com.example.app.MainActivity
import com.example.app.components.Item

@Composable
fun TelaItems(navController: NavController) {
    Column(
        modifier = Modifier
            .padding(all = 40.dp)
    ) {
        BackButton(navController, "Voltar")
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(20.dp),
            userScrollEnabled = true
        ) {
            itemsIndexed(MainActivity.estoque.getListaProdutos()) {
                    index, produto -> Item(
                number = index,
                produto = produto,
                navController = navController
            )
            }
        }
        Spacer(modifier=Modifier.height(20.dp))
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                navController.navigate("estatisticas")
            }
        ) {
            Text("Ver Soma", color = Color.White)
        }
    }
}