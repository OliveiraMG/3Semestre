package org.example;

public class Servico implements Vendavel{
    private String descricao;
    private Integer codigo;

    private Integer qtdHrsServico;
    private Double valorHora;

    public Servico(String descricao, Integer codigo, Integer qtdHrsServico, Double valorHora) {
        this.descricao = descricao;
        this.codigo = codigo;
        this.qtdHrsServico = qtdHrsServico;
        this.valorHora = valorHora;
    }

    @Override
    public Double getValorVenda() {
        return qtdHrsServico * valorHora;
    }

    @Override
    public String toString() {
        return "Servico{" +
                "descricao='" + descricao + '\'' +
                ", codigo=" + codigo +
                ", qtdHrsServico=" + qtdHrsServico +
                ", valorHora=" + valorHora +
                ", valorVenda=" + getValorVenda() +
                '}';
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getQtdHrsServico() {
        return qtdHrsServico;
    }

    public void setQtdHrsServico(Integer qtdHrsServico) {
        this.qtdHrsServico = qtdHrsServico;
    }

    public Double getValorHora() {
        return valorHora;
    }

    public void setValorHora(Double valorHora) {
        this.valorHora = valorHora;
    }
}
