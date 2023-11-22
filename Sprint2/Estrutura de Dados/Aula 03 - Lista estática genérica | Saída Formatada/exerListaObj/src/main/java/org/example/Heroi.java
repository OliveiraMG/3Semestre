package org.example;

public class Heroi {
    private int id;
    private String nome;
    private String poder;
    private String fraqueza;
    private double altura;
    private double peso;
    private boolean temCapa;

    public Heroi(int id, String nome, String poder, String fraqueza, double altura, double peso, boolean temCapa) {
        this.id = id;
        this.nome = nome;
        this.poder = poder;
        this.fraqueza = fraqueza;
        this.altura = altura;
        this.peso = peso;
        this.temCapa = temCapa;
    }

    // Métodos getters
    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getPoder() {
        return poder;
    }

    public String getFraqueza() {
        return fraqueza;
    }

    public double getAltura() {
        return altura;
    }

    public double getPeso() {
        return peso;
    }

    public boolean isTemCapa() {
        return temCapa;
    }

    // Métodos setters
    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setPoder(String poder) {
        this.poder = poder;
    }

    public void setFraqueza(String fraqueza) {
        this.fraqueza = fraqueza;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public void setTemCapa(boolean temCapa) {
        this.temCapa = temCapa;
    }

    @Override
    public String toString() {
        return String.format("%d\t%s\t%s\t%s\t%.2f\t%.2f\t%b", id, nome, poder, fraqueza, altura, peso, temCapa);
    }
}
