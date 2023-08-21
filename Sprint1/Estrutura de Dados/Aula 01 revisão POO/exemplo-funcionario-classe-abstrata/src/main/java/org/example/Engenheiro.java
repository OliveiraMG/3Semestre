package org.example;

public class Engenheiro extends Funcionario{

    // Atributos
    private Double salario;

    // Construtor

    public Engenheiro(String cpf, String nome, Double salario) {
        super(cpf, nome);
        this.salario = salario;
    }

    // Métodos

    /* Implementação calcSalario */

    @Override
    public Double calcSalario() {
        return salario;
    }

    @Override
    public String toString() {
        return "Engenheiro{" +
                "salario=" + salario +
                "} " + super.toString();
    }
}
