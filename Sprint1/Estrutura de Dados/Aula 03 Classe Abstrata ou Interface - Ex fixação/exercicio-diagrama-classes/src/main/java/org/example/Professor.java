package org.example;

public class Professor extends Funcionario implements BonusCalculavel {
    private int quantidadeAulas;
    private double valorHoraAula;

    public Professor(String nome, int quantidadeAulas, double valorHoraAula) {
        super(nome);
        this.quantidadeAulas = quantidadeAulas;
        this.valorHoraAula = valorHoraAula;
    }

    @Override
    public double getValorBonus() {
        return quantidadeAulas * valorHoraAula * 4.5 * 0.15;
    }

    @Override
    public String toString() {
        return super.toString() + ", Bônus: " + getValorBonus();
    }
}

