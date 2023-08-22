package com.example.estoque.matheus.entity;

public class Produto {
    private String nome;
    private double preco;
    private int qtdEstoque;
    private double valorTotalEstoque;
    public Produto() {
    }

    public Produto(String nome, double preco, int qtdEstoque, double valorTotalEstoque) {
        this.nome = nome;
        this.preco = preco;
        this.qtdEstoque = qtdEstoque;
        this.valorTotalEstoque = 0.0;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Integer getQtdEstoque() {
        return qtdEstoque;
    }

    public void setQtdEstoque(Integer qtdEstoque) {
        this.qtdEstoque = qtdEstoque;
    }

    public Double getValorTotalEstoque() {
        return getPreco() * getQtdEstoque();
    }

    public void setValorTotalEstoque(Double valorTotalEstoque) {
        this.valorTotalEstoque = valorTotalEstoque;
    }
}
