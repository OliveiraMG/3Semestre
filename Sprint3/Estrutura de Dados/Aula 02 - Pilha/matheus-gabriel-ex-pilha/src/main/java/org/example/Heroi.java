package org.example;

public class Heroi {
    private int id;
    private String nome;
    private String poder;
    private String fraqueza;
    private Double nivelForca;

    public Heroi(int id, String nome, String poder, String fraqueza, Double nivelForca) {
        this.id = id;
        this.nome = nome;
        poder = poder;
        this.fraqueza = fraqueza;
        this.nivelForca = nivelForca;
    }

    @Override
    public String toString() {
        return "Heroi{" +
                "id='" + id + '\'' +
                ", nome='" + nome + '\'' +
                ", Poder='" + poder + '\'' +
                ", fraqueza='" + fraqueza + '\'' +
                ", nivelForca=" + nivelForca +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPoder() {
        return poder;
    }

    public void setPoder(String poder) {
        poder = poder;
    }

    public String getFraqueza() {
        return fraqueza;
    }

    public void setFraqueza(String fraqueza) {
        this.fraqueza = fraqueza;
    }

    public Double getNivelForca() {
        return nivelForca;
    }

    public void setNivelForca(Double nivelForca) {
        this.nivelForca = nivelForca;
    }
}