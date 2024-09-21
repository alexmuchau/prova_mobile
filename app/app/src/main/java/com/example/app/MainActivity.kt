package com.example.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.app.classes.Estoque
import com.example.app.classes.Produto
import com.example.app.screens.TelaCadastro
import com.example.app.screens.TelaDetalhamento
import com.example.app.screens.TelaEstatistica
import com.example.app.screens.TelaItems
import com.example.app.ui.theme.AppTheme
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    LayoutMain()
                }
            }
        }
    }

    companion object {
        val estoque = Estoque()
    }
}

@Composable
fun LayoutMain() {
    val navController = rememberNavController()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 0.dp, vertical = 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        NavHost(navController = navController, startDestination="home") {
            composable("home") { TelaCadastro(navController) }
            composable("produtos") { TelaItems(navController=navController) }
            composable("produto/{produtoJson}") {
                backStackEntry ->
                val produtoJson = backStackEntry.arguments?.getString("produtoJson")
                val sType = object : TypeToken<Produto>() { }.type

                val produto = Gson().fromJson<Produto>(produtoJson, sType)

                TelaDetalhamento(produto = produto, navController = navController)
            }
            composable("estatisticas") { TelaEstatistica(navController=navController) }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    AppTheme {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            LayoutMain()
        }
    }
}