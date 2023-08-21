package org.example;

import java.util.ArrayList;
import java.util.List;

public class ControleBonus {
    private List<BonusCalculavel> listaFuncionariosBonus;

    public ControleBonus() {
        listaFuncionariosBonus = new ArrayList<>();
    }

    public void adicionarFuncionario(BonusCalculavel funcionario) {
        listaFuncionariosBonus.add(funcionario);
    }

    public void exibirFuncionarios() {
        for (BonusCalculavel funcionario : listaFuncionariosBonus) {
            System.out.println(funcionario);
        }
    }

    public double calcularTotalBonus() {
        double totalBonus = 0;
        for (BonusCalculavel funcionario : listaFuncionariosBonus) {
            totalBonus += funcionario.getValorBonus();
        }
        return totalBonus;
    }
}
