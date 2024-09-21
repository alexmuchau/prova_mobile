package com.example.app

class Estoque {
    private val listaProdutos = mutableListOf<Produto>()

    fun adicionarProduto(produto: Produto) {
        listaProdutos.add(produto)
    }

    fun calcularValorTotalEstoque(): Double {
        return listaProdutos.sumOf { it.preco * it.qtd_estoque }
    }

    fun getListaProdutos(): List<Produto> {
        return listaProdutos
    }
}