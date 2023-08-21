package org.example;

public class Dvd extends Produto {

    private String nome;
    private String gravadora;

    public Dvd(Integer codigo, Double precoCusto, String nome, String gravadora) {
        super(codigo, precoCusto);
        this.nome = nome;
        this.gravadora = gravadora;
    }

    @Override
    public Double getValorVenda() {
        Double valorAdicional = getPrecoCusto() * 0.20;
        return valorAdicional + getPrecoCusto();
    }

    @Override
    public String toString() {
        return "Dvd{" +
                "nome='" + nome + '\'' +
                ", gravadora='" + gravadora + '\'' +
                ", Valor venda='" + getValorVenda() + '\'' +
                "} " + super.toString();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getGravadora() {
        return gravadora;
    }

    public void setGravadora(String gravadora) {
        this.gravadora = gravadora;
    }
}
