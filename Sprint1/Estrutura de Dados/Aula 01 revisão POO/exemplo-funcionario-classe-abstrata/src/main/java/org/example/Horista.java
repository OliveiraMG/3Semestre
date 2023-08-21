package org.example;

public class Horista extends Funcionario{
    private Integer qtdHoras;
    private Double valorHora;

    public Horista(String cpf, String nome, Integer qtdHoras, Double valorHora) {
        super(cpf, nome);
        this.qtdHoras = qtdHoras;
        this.valorHora = valorHora;
    }

    @Override
    public Double calcSalario() {
        return qtdHoras * valorHora;
    }

    @Override
    public String toString() {
        return "Horista{" +
                "qtdHoras=" + qtdHoras +
                ", valorHora=" + valorHora +
                "} " + super.toString();
    }
}
