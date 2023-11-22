package org.example;

import java.util.ArrayList;
import java.util.List;

public class Repositorio {
    FilaObj<Heroi> fila;
    List<Heroi> lista;
    PilhaObj<Integer> pilha;

    public Repositorio(List<Heroi> lista, PilhaObj<Integer> pilha) {
        this.fila = new FilaObj<>(10);
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
        fila.exibe();
    }

    public void desfazer(){
        if (pilha.isEmpty()) {
            System.out.println("Não há nada a desfazer");
        } else {
            int id = pilha.pop();
            deletar(id);
        }
    }

    public void agendarSalvar(Heroi heroi){
        fila.insert(heroi);
    }

    public void executarAgendado(int qtdOperacoes){
        if (fila.isEmpty()) {
            System.out.println("Não há operações agendadas");
        } else {
            if (qtdOperacoes <= 0 || qtdOperacoes > fila.tamanho) {
                System.out.println("Quantidade inválida");
            } else {
                for (int i = 0; i < qtdOperacoes; i++) {
                    Heroi heroi = fila.poll();
                    salvar(heroi);
                }
            }
        }
    }


}
