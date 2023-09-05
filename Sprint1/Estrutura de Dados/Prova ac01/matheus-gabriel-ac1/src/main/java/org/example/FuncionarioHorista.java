package org.example;

public class FuncionarioHorista extends Funcionario{

    private double qtdhoras;
    private double valorHoras;

    public FuncionarioHorista(Integer codigo, String nome, double qtdhoras, double valorHoras) {
        super(codigo, nome);
        this.qtdhoras = qtdhoras;
        this.valorHoras = valorHoras;
    }

    @Override
    public String toString() {
        return "FuncionarioHorista{" +
                "qtdhoras=" + qtdhoras +
                ", valorHoras=" + valorHoras +
                ", ganho=" + getGanho() +
                ", bonus=" + getValorBonus() +
                "} " + super.toString();
    }

    @Override
    public Double getGanho() {
        return qtdhoras * valorHoras;
    }

    @Override
    public Double getValorBonus() {
        return getGanho() * 0.10;
    }

    public double getQtdhoras() {
        return qtdhoras;
    }

    public void setQtdhoras(double qtdhoras) {
        this.qtdhoras = qtdhoras;
    }

    public double getValorHoras() {
        return valorHoras;
    }

    public void setValorHoras(double valorHoras) {
        this.valorHoras = valorHoras;
    }
}
