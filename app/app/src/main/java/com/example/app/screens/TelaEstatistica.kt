package com.example.app.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.app.MainActivity
import com.example.app.components.BackButton
import com.example.app.components.Title

@Composable
fun TelaEstatistica(navController: NavController) {
    val somaProduto = MainActivity.estoque.calcularValorTotalEstoque()
    val somaQtdProduto = MainActivity.estoque.calcularUnidadesEmEstoque()

    BackButton(navController=navController, label="Voltar")
    Column (
        modifier = Modifier
            .padding(all = 40.dp),
    ) {
        Title("Valor total em produtos")
        Text("R$${somaProduto}")
        Spacer(modifier=Modifier.height(20.dp))
        Title("Quantidade em estoque total")
        Text("${somaQtdProduto} un")
    }
}