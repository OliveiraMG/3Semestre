package org.example;

public class Alimento extends Produto {

    private Integer qtdVitamina;

    public Alimento(Integer codigo, String descricao, Double preco, Integer qtdVitamina) {
        super(codigo, descricao, preco);
        this.qtdVitamina = qtdVitamina;
    }

    @Override
    public Double calculaValorTributo() {
        Double valorICMS = getPreco() * 0.15;
        Double precoFinal = getPreco() - valorICMS;
        System.out.println("Valor do ICMS alimento: " + valorICMS + " Preço final: " + precoFinal);
        return valorICMS;
    }

    @Override
    public String toString() {
        return "Alimento{" +
                "qtdVitamina=" + qtdVitamina +
                "} " + super.toString();
    }

    public Integer getQtdVitamina() {
        return qtdVitamina;
    }

    public void setQtdVitamina(Integer qtdVitamina) {
        this.qtdVitamina = qtdVitamina;
    }
}
