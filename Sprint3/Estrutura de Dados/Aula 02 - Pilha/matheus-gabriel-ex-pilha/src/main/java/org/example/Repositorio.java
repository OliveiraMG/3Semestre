package org.example;

import java.util.ArrayList;
import java.util.List;

public class Repositorio {

    List<Heroi> lista;
    PilhaObj<Integer> pilha;

    public Repositorio(List<Heroi> lista, PilhaObj<Integer> pilha) {
        this.pilha = (PilhaObj<Integer>) new PilhaObj(10);
        this.lista = new ArrayList<>();
    }

    public void salvar(Heroi heroi){
        lista.add(heroi);
        pilha.push(heroi.getId());
    }

    public void deletar(int id){
        for (Heroi heroi : lista) {
            if (heroi.getId() == id) {
                lista.remove(heroi);
                return;
            }
        }
        System.out.println("ID inexistente");
    }

    public void exibir(){
        if (lista.isEmpty()) {
            System.out.println("Repositório vazio");
        } else {
            for (Heroi heroi : lista) {
                System.out.println(heroi);
            }
        }
        pilha.exibe();
    }

    public void desfazer(){
        if (pilha.isEmpty()) {
            System.out.println("Não há nada a desfazer");
        } else {
            int id = pilha.pop();
            deletar(id);
        }
    }


}
