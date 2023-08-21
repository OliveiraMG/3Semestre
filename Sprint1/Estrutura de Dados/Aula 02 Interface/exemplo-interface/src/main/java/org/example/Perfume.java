package org.example;

public class Perfume extends Produto {

    private String fragrancia;

    public Perfume(Integer codigo, String descricao, Double preco, String fragrancia) {
        super(codigo, descricao, preco);
        this.fragrancia = fragrancia;
    }

    @Override
    public Double calculaValorTributo() {
        Double valorICMS = getPreco() * 0.27;
        Double precoFinal = getPreco() - valorICMS;
        System.out.println("Valor do ICMS perfume: " + valorICMS + " Preço final: " + precoFinal);
        return valorICMS;
    }

    @Override
    public String toString() {
        return "Perfume{" +
                "fragrancia='" + fragrancia + '\'' +
                "} " + super.toString();
    }

    public String getFragrancia() {
        return fragrancia;
    }

    public void setFragrancia(String fragrancia) {
        this.fragrancia = fragrancia;
    }
}
