package com.example.app

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.app.ui.theme.AppTheme
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlin.math.round

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LayoutMain()
        }
    }

    companion object {
        var listaProdutos = mutableListOf<Produto>()
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
            composable("home") {TelaCadastro(navController)}
            composable("produtos") { TelaItems(navController=navController) }
            composable("produto/{produtoJson}") {
                backStackEntry ->
                val produtoJson = backStackEntry.arguments?.getString("produtoJson")
                val sType = object : TypeToken<Produto>() { }.type

                val produto = Gson().fromJson<Produto>(produtoJson, sType)

                TelaDetalhamento(produto = produto, navController = navController)
            }
        }
    }
}

@Composable
fun Title(text: String){
    Text(
        text=text.uppercase(),
        fontSize=50.sp,
        fontWeight = FontWeight.Bold,
        lineHeight = 50.sp
    )
}

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
        )
    }
}

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

@Composable
fun TelaItems(navController: NavController) {
    Column(
        modifier = Modifier
            .padding(all = 40.dp),
    ) {
        BackButton(navController, "Voltar")
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(50.dp)
        ) {
            itemsIndexed(MainActivity.listaProdutos) {
                    index, produto -> Item(
                number = index,
                produto = produto,
                navController = navController
            )
            }
        }
    }
}

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
            label = { Text(text = "Nome do produto")})

        TextField(modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
            value = categoriaProduto,
            onValueChange = { categoriaProduto = it },
            label = { Text(text = "Categoria do produto")})

        TextField(modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
            value = precoProduto,
            onValueChange = { precoProduto = it },
            label = { Text(text = "Preço do produto")})

        TextField(modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
            value = estoqueProduto,
            onValueChange = { estoqueProduto = it },
            label = { Text(text = "Quantidade em estoque")})

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                if (nomeProduto.isNotBlank()
                    && categoriaProduto.isNotBlank()
                    && precoProduto.isNotBlank()
                    && estoqueProduto.isNotBlank()
                    ) {
                    val produto = Produto(nomeProduto, categoriaProduto, precoProduto, estoqueProduto)
                    MainActivity.listaProdutos.add(produto)
//                    Toast.makeText(context, "Item adicionado", Toast.LENGTH_SHORT).show()

                    nomeProduto = ""
                    categoriaProduto = ""
                    precoProduto = ""
                    estoqueProduto = ""
                } else {
                    Toast.makeText(context, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
                }
            }
        ) {
            Text(text = "Adicionar", textAlign = TextAlign.Left)
        }

        Spacer(modifier = Modifier.height(50.dp))

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                navController.navigate("produtos")
            }
        ) {
            Text("Ver Produtos")
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