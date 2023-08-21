package org.example;

public class Servico implements Tributavel{

    private String descricao;
    private Double preco;

    public Servico(String descricao, Double preco) {
        this.descricao = descricao;
        this.preco = preco;
    }

    @Override
    public Double calculaValorTributo() {
        Double valorISS = preco * 0.12;
        Double precoFinal = preco - valorISS;
        System.out.println("Valor do ISS: " + valorISS + " Preço final: " + precoFinal);
        return valorISS;
    }

    @Override
    public String toString() {
        return "Servico{" +
                "descricao='" + descricao + '\'' +
                ", preco=" + preco +
                '}';
    }
}
