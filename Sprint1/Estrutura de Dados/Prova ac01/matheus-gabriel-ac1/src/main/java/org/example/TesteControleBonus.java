package org.example;

public class TesteControleBonus {
    public static void main(String[] args) {

        FuncionarioHorista fh = new FuncionarioHorista(1, "João", 160, 100);
        FuncionarioHrExtra fhe = new FuncionarioHrExtra(2, "Maria", 160, 100, 20);

        Acionista a = new Acionista("José", 100, 10);

        System.out.println("Ganhos funcionário horista: " + fh.getGanho() + " e funcionário hora extra: " + fhe.getGanho());
        System.out.println("Ganhos acionista: " + a.calcularTotalAcoes());
        System.out.println("Valor bonus de funcionário horista: " + fh.getValorBonus() + ", valor bonus de funcionario hora extra: " + fhe.getValorBonus() + " e valor bonus acionista: " + a.getValorBonus());

        ControleBonus controle = new ControleBonus();

        controle.addBonusCalculavel(fh);
        controle.addBonusCalculavel(fhe);
        controle.addBonusCalculavel(a);

        if (controle.getListaBonusCalculaveis().isEmpty()) {
            System.out.println("Lista vazia!");
        } else {
            controle.getTotalGastoBonus();
        }

    }
}