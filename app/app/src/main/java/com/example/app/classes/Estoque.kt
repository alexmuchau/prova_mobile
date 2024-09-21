package com.example.app.classes

class Estoque {
    private val listaProdutos = mutableListOf<Produto>(
        Produto(
            nome = "Produto1",
            categoria = "Cat1",
            qtd_estoque = 2,
            preco = 22.0
        ),
        Produto(
            nome = "Produto2",
            categoria = "Cat1",
            qtd_estoque = 1,
            preco = 13.0
        ),
        Produto(
            nome = "Produto3",
            categoria = "Cat2",
            qtd_estoque = 5,
            preco = 2.0
        ),
        Produto(
            nome = "Produto4",
            categoria = "Cat2",
            qtd_estoque = 2,
            preco = 30.0
        ),
        Produto(
            nome = "Produto5",
            categoria = "Cat1",
            qtd_estoque = 2,
            preco = 100.0
        ),
    )

    fun adicionarProduto(produto: Produto) {
        listaProdutos.add(produto)
    }

    fun calcularValorTotalEstoque(): Double {
        return listaProdutos.sumOf { it.preco * it.qtd_estoque }
    }

    fun calcularUnidadesEmEstoque(): Int {
        return listaProdutos.sumOf { it.qtd_estoque }
    }

    fun getListaProdutos(): List<Produto> {
        return listaProdutos
    }
}