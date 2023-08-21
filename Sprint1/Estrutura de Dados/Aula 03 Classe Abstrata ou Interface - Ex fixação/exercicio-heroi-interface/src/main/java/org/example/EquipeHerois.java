package org.example;

import java.util.ArrayList;
import java.util.List;

public class EquipeHerois {
    List<Heroi> listaHerois;

    public EquipeHerois() {
        listaHerois = new ArrayList<>();
    }

    public void adicionarHeroi(Heroi heroi) {
        listaHerois.add(heroi);
    }

    public void removerHeroi(Heroi heroi) {
        listaHerois.remove(heroi);
    }

    public void listarHerois() {
        for (Heroi heroi : listaHerois) {
            System.out.println(heroi.getNome());
        }
    }
}
