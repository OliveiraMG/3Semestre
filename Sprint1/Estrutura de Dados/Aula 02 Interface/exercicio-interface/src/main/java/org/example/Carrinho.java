package org.example;

import java.util.ArrayList;
import java.util.List;

public class Carrinho {
    private List<Vendavel> listaVendaveis;


    public Carrinho() {
        listaVendaveis = new ArrayList<>();
    }


    public void addVendavel(Vendavel vendavel) {
        listaVendaveis.add(vendavel);
    }

    public void calculaTotalVenda() {
        Double total = 0.0;
        for (Vendavel vendavel : listaVendaveis) {
            total += vendavel.getValorVenda();
        }
        System.out.println("Total da venda: " + total);
    }

    public void exibeItensCarrinho() {
        for (Vendavel vendavel : listaVendaveis) {
            System.out.println(vendavel);
        }
    }

    public List<Vendavel> getListaVendaveis() {
        return listaVendaveis;
    }
}
