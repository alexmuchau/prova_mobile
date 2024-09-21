package com.example.app.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.app.MainActivity
import com.example.app.classes.Produto
import com.example.app.components.Title

@Composable
fun TelaCadastro(navController: NavController) {
    var nomeProduto by remember { mutableStateOf("") }
    var categoriaProduto by remember { mutableStateOf("") }
    var precoProduto by remember { mutableStateOf("") }
    var estoqueProduto by remember { mutableStateOf("") }

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .padding(all = 40.dp),
    ) {
        Title("Adicionar produto ao estoque")
        TextField(modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
            value = nomeProduto,
            onValueChange = { nomeProduto = it },
            label = { Text(text = "Nome do produto") })

        TextField(modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
            value = categoriaProduto,
            onValueChange = { categoriaProduto = it },
            label = { Text(text = "Categoria do produto") })

        TextField(modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
            value = precoProduto,
            onValueChange = { precoProduto = it },
            label = { Text(text = "Preço do produto") })

        TextField(modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
            value = estoqueProduto,
            onValueChange = { estoqueProduto = it },
            label = { Text(text = "Quantidade em estoque") })

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                if (nomeProduto.isNotBlank()
                    && categoriaProduto.isNotBlank()
                    && precoProduto.isNotBlank()
                    && estoqueProduto.isNotBlank()
                ) {

                    val preco = precoProduto.toDoubleOrNull()
                    val qtd_estoque = estoqueProduto.toIntOrNull()

                    if (preco == null || preco < 0f) {
                        Toast.makeText(
                            context,
                            "Preencha o campo de preço corretamente",
                            Toast.LENGTH_SHORT
                        ).show()
                        return@Button
                    }

                    if (qtd_estoque == null || qtd_estoque < 1) {
                        Toast.makeText(
                            context,
                            "Preencha o campo de quantidade corretamente",
                            Toast.LENGTH_SHORT
                        ).show()
                        return@Button
                    }

                    val produto = Produto(nome=nomeProduto, categoria = categoriaProduto, preco=preco, qtd_estoque=qtd_estoque)
                    MainActivity.estoque.adicionarProduto(produto)
                    Toast.makeText(context, "Item adicionado!", Toast.LENGTH_SHORT).show()

                    nomeProduto = ""
                    categoriaProduto = ""
                    precoProduto = ""
                    estoqueProduto = ""

                    return@Button
                }
                Toast.makeText(context, "Preencha todos os campos coretamente", Toast.LENGTH_SHORT).show()
            }
        ) {
            Text(text = "Adicionar", color = Color.White)
        }

        Spacer(modifier = Modifier.height(50.dp))

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                navController.navigate("produtos")
            }
        ) {
            Text("Ver Produtos", color = Color.White)
        }
    }
}