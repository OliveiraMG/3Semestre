package com.example.estoque.matheus.controller;

import com.example.estoque.matheus.entity.Produto;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private List<Produto> listaProdutos = new ArrayList<>();

    // https://localhost:8080/produtos
    @PostMapping
    public Produto cadastrarProduto(@RequestBody Produto produto) {
        listaProdutos.add(produto);
        return produto;
    }

    // https://localhost:8080/produtos
    @GetMapping
    public List<Produto> listarProdutos() {
        return listaProdutos;
    }

    // https://localhost:8080/produtos/0
    @PutMapping("/{indice}")
    public Produto editarProduto(@PathVariable int indice, @RequestBody Produto produto) {
        if (isIndiceValido(indice)) {
            listaProdutos.set(indice, produto);
            return produto;
        } else {
            return null;
        }
    }

    // https://localhost:8080/produtos/estoque/{qtdEstoque}
    @GetMapping("/estoque/{qtdEstoque}")
    public List<Produto> produtosQtdMaiorQueDez() {
        List<Produto> produtosQtdMaiorQueDez = new ArrayList<>();
        for (Produto produto : listaProdutos) {
            if (produto.getQtdEstoque() > 10) {
                produtosQtdMaiorQueDez.add(produto);
            }
        }
        return produtosQtdMaiorQueDez;
    }

    private boolean isIndiceValido(int indice) {
        return indice >= 0 && indice < listaProdutos.size();
    }

}
