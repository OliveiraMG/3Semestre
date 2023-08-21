package org.example;

import java.util.ArrayList;
import java.util.List;

public class Tributo {

    private List<Tributavel> listaTributos;

    public Tributo() {
        listaTributos = new ArrayList<>();
    }

    public void addTributavel(Tributavel tributo) {
        listaTributos.add(tributo);
    }

    public void exibeTodos() {
        for (Tributavel tributo : listaTributos) {
            System.out.println(tributo);
        }
    }

    public Double calcularTributos() {
        Double total = 0.0;
        for (Tributavel tributo : listaTributos) {
            total += tributo.calculaValorTributo();
        }
        return total;
    }

}
