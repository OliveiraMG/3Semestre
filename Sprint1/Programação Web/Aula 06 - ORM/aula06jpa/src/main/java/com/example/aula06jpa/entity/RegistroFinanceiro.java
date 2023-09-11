package com.example.aula06jpa.entity;

import com.example.aula06jpa.dtos.RegistroFinanceiroDTO;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity(name = "registro_financeiro")
@Table(name = "registro_financeiro")
public class RegistroFinanceiro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descricao;
    private Double valor;
    private LocalDate data;

    public RegistroFinanceiro() {
    }

    public RegistroFinanceiro(RegistroFinanceiroDTO data) {
        this.descricao = data.descricao();
        this.valor = data.valor();
        this.data = LocalDate.parse(data.data());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }
}
