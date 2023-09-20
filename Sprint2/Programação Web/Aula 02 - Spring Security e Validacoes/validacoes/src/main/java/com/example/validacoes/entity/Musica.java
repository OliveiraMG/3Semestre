package com.example.validacoes.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

@Entity
@Table(name = "musica")
public class Musica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank
    private String nome;
    @Size(min = 3, max = 10)
    @NotBlank
    private String album;
    @PastOrPresent
    private LocalDate dataLancamento;
    @DecimalMin(value = "1.0", inclusive = true)
    @DecimalMax(value = "5.0", inclusive = true)
    @PositiveOrZero
    private Double nota;
    @Min(1)
    private int rankingBillboard;
    @Email
    private String email;
    @CPF
    private String cpfResponsavel;
    @CNPJ
    private String cnpjProdutora;
    @Pattern(regexp = "\\(\\d{2}\\)\\d{4,5}-\\d{4}")
    private String telefoneContato;

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

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public LocalDate getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(LocalDate dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }

    public int getRankingBillboard() {
        return rankingBillboard;
    }

    public void setRankingBillboard(int rankingBillboard) {
        this.rankingBillboard = rankingBillboard;
    }
}
