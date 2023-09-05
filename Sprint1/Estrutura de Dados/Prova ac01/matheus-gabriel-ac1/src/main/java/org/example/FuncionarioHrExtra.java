package org.example;

public class FuncionarioHrExtra extends Funcionario{

    private double salario;
    private double qtdHrExtra;
    private double valorHrExtra;

    public FuncionarioHrExtra(Integer codigo, String nome, double salario, double qtdHrExtra, double valorHrExtra) {
        super(codigo, nome);
        this.salario = salario;
        this.qtdHrExtra = qtdHrExtra;
        this.valorHrExtra = valorHrExtra;
    }

    @Override
    public String toString() {
        return "FuncionarioHrExtra{" +
                "salario=" + salario +
                ", qtdHrExtra=" + qtdHrExtra +
                ", valorHrExtra=" + valorHrExtra +
                ", ganho=" + getGanho() +
                ", bonus=" + getValorBonus() +
                "} " + super.toString();
    }

    @Override
    public Double getGanho() {
        return salario + (qtdHrExtra * valorHrExtra);
    }

    @Override
    public Double getValorBonus() {
        return getGanho() * 0.15;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public double getQtdHrExtra() {
        return qtdHrExtra;
    }

    public void setQtdHrExtra(double qtdHrExtra) {
        this.qtdHrExtra = qtdHrExtra;
    }

    public double getValorHrExtra() {
        return valorHrExtra;
    }

    public void setValorHrExtra(double valorHrExtra) {
        this.valorHrExtra = valorHrExtra;
    }
}
