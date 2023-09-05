package org.example;

public class Acionista implements BonusCalculavel{
    private String nome;
    private int qtdAcoes;
    private double valorAcao;

    public Acionista(String nome, int qtdAcoes, double valorAcao) {
        this.nome = nome;
        this.qtdAcoes = qtdAcoes;
        this.valorAcao = valorAcao;
    }

    @Override
    public String toString() {
        return "Acionista{" +
                "nome='" + nome + '\'' +
                ", qtdAcoes=" + qtdAcoes +
                ", valorAcao=" + valorAcao +
                ", bonus=" + getValorBonus() +
                '}';
    }

    public Double calcularTotalAcoes() {
        return qtdAcoes * valorAcao;
    }

    @Override
    public Double getValorBonus() {
        return calcularTotalAcoes() * 0.20;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQtdAcoes() {
        return qtdAcoes;
    }

    public void setQtdAcoes(int qtdAcoes) {
        this.qtdAcoes = qtdAcoes;
    }

    public double getValorAcao() {
        return valorAcao;
    }

    public void setValorAcao(double valorAcao) {
        this.valorAcao = valorAcao;
    }
}
