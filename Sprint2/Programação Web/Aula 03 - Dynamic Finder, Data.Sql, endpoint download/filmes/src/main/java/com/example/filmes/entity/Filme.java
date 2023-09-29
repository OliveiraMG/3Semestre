package com.example.filmes.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

@Entity
@Table(name = "filme")
public class Filme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank
    @Size(max = 150)
    private String nome;
    @NotBlank
    private String diretor;
    @Past
    private LocalDate dataLancamento;
    @DecimalMin(value = "0.1", inclusive = true)
    private Double custoProducao;
    private boolean indicacaoOscar;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDiretor() {
        return diretor;
    }

    public void setDiretor(String diretor) {
        this.diretor = diretor;
    }

    public LocalDate getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(LocalDate dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public Double getCustoProducao() {
        return custoProducao;
    }

    public void setCustoProducao(Double custoProducao) {
        this.custoProducao = custoProducao;
    }

    public boolean isIndicacaoOscar() {
        return indicacaoOscar;
    }

    public void setIndicacaoOscar(boolean indicacaoOscar) {
        this.indicacaoOscar = indicacaoOscar;
    }
}
