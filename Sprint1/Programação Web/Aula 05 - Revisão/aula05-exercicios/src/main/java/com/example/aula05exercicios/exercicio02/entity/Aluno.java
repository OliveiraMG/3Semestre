package com.example.aula05exercicios.exercicio02.entity;

public class Aluno {
    private String nome;
    private String email;
    private double notaContinuada1;
    private double notaContinuada2;
    private double notaContinuada3;
    private double notaIntegrada;

    public Aluno() {
    }

    public Aluno(String nome, String email, double notaContinuada1, double notaContinuada2, double notaContinuada3, double notaIntegrada) {
        this.nome = nome;
        this.email = email;
        this.notaContinuada1 = notaContinuada1;
        this.notaContinuada2 = notaContinuada2;
        this.notaContinuada3 = notaContinuada3;
        this.notaIntegrada = notaIntegrada;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getNotaContinuada1() {
        return notaContinuada1;
    }

    public void setNotaContinuada1(double notaContinuada1) {
        this.notaContinuada1 = notaContinuada1;
    }

    public double getNotaContinuada2() {
        return notaContinuada2;
    }

    public void setNotaContinuada2(double notaContinuada2) {
        this.notaContinuada2 = notaContinuada2;
    }

    public double getNotaContinuada3() {
        return notaContinuada3;
    }

    public void setNotaContinuada3(double notaContinuada3) {
        this.notaContinuada3 = notaContinuada3;
    }

    public double getNotaIntegrada() {
        return notaIntegrada;
    }

    public void setNotaIntegrada(double notaIntegrada) {
        this.notaIntegrada = notaIntegrada;
    }

    public double getNotaFinal() {
        return ((notaContinuada1 + notaContinuada2 + notaContinuada3) / 3) * 0.4 + (notaIntegrada * 0.6);
    }
}
