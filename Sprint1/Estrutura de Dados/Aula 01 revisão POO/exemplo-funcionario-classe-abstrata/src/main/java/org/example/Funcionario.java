package org.example;

abstract public class Funcionario {

    // Atributos
    private String cpf;
    private String nome;

    // Constructor

    public Funcionario(String cpf, String nome) {
        this.cpf = cpf;
        this.nome = nome;
    }

    // Métodos

    /* Método abstrato calcSalario()*/

    abstract public Double calcSalario();

    /* Método toString() */
    @Override
    public String toString() {
        return "Funcionario{" +
                "cpf='" + cpf + '\'' +
                ", nome='" + nome + '\'' +
                ", salario='" + calcSalario() + '\'' +
                '}';
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
