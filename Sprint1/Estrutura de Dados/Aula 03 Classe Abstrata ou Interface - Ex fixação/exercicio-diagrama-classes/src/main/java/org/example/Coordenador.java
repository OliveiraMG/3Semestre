package org.example;

public class Coordenador extends Funcionario implements BonusCalculavel {
    private int horasCoordenacao;
    private double valorHoraCoordenacao;

    public Coordenador(String nome, int horasCoordenacao, double valorHoraCoordenacao) {
        super(nome);
        this.horasCoordenacao = horasCoordenacao;
        this.valorHoraCoordenacao = valorHoraCoordenacao;
    }

    @Override
    public double getValorBonus() {
        return horasCoordenacao * valorHoraCoordenacao * 4.5 * 0.2;
    }

    @Override
    public String toString() {
        return super.toString() + ", Bônus: " + getValorBonus();
    }
}

