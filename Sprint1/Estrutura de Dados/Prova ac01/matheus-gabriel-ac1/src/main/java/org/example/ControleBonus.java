package org.example;

import java.util.ArrayList;
import java.util.List;

public class ControleBonus {

    private List<BonusCalculavel> listaBonusCalculaveis;

    public ControleBonus() {
        listaBonusCalculaveis = new ArrayList<>();
    }

    public void addBonusCalculavel(BonusCalculavel bonusCalculavel) {
        listaBonusCalculaveis.add(bonusCalculavel);
    }

    public void getTotalGastoBonus() {
        double total = 0.0;
        for (BonusCalculavel bonusCalculavel : listaBonusCalculaveis) {
            total += bonusCalculavel.getValorBonus();
        }
        System.out.println("Total bônus: " + total);;
    }

    public List<BonusCalculavel> getListaBonusCalculaveis() {
        return listaBonusCalculaveis;
    }
}
