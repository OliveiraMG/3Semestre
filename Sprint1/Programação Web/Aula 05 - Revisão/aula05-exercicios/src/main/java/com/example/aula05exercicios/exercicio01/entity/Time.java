package com.example.aula05exercicios.exercicio01.entity;

public class Time {

    private String nome;

    private String treinador;

    private int vitorias;

    private int derrotas;

    private int empates;

    public Time() {

    }

    public Time(String nome, String treinador, int vitorias, int derrotas, int empates) {
        this.nome = nome;
        this.treinador = treinador;
        this.vitorias = vitorias;
        this.derrotas = derrotas;
        this.empates = empates;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTreinador() {
        return treinador;
    }

    public void setTreinador(String treinador) {
        this.treinador = treinador;
    }

    public int getVitorias() {
        return vitorias;
    }

    public void setVitorias(int vitorias) {
        this.vitorias = vitorias;
    }

    public int getDerrotas() {
        return derrotas;
    }

    public void setDerrotas(int derrotas) {
        this.derrotas = derrotas;
    }

    public int getEmpates() {
        return empates;
    }

    public void setEmpates(int empates) {
        this.empates = empates;
    }

    public int getPontuacaoTotal() {
        return (vitorias * 3) + empates;
    }

    public double getAproveitamento() {
        return (getPontuacaoTotal() * 100) / (vitorias + derrotas + empates);
    }

    @Override
    public String toString() {
        return "Time{" +
                "nome='" + nome + '\'' +
                ", treinador='" + treinador + '\'' +
                ", vitorias=" + vitorias +
                ", derrotas=" + derrotas +
                ", empates=" + empates +
                '}';
    }
}
