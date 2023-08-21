package org.example;

public class Main {
    public static void main(String[] args) {
        Professor professor = new Professor("João", 20, 50.0);
        Coordenador coordenador = new Coordenador("Maria", 10, 80.0);

        ControleBonus controleBonus = new ControleBonus();
        controleBonus.adicionarFuncionario(professor);
        controleBonus.adicionarFuncionario(coordenador);

        controleBonus.exibirFuncionarios();
        System.out.println("Total de bônus: " + controleBonus.calcularTotalBonus());
    }
}