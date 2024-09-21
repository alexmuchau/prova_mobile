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
import com.example.app.BackButton
import com.example.app.Produto

@Composable
fun TelaDetalhamento(produto: Produto, navController: NavController) {
    Column(
        modifier = Modifier
            .padding(all = 40.dp),
    ) {
        BackButton(navController, "Voltar")
        Title(produto.nome)
        Column (
        ){
            Spacer(modifier = Modifier.height(50.dp))
            Text("Preço - ${produto.preco}")
            Text("Categoria - ${ produto.categoria }")
            Text("Quantidade em estoque - ${ produto.qtd_estoque }")
        }
    }
}