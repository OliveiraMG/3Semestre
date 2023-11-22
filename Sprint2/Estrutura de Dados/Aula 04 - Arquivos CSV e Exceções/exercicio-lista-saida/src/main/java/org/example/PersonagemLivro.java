package org.example;

public class PersonagemLivro {
    private int id;
    private String nome;
    private String habilidade;
    private double forca;
    private int idade;
    private String reino;
    private boolean protagonista;

    // Construtor
    public PersonagemLivro(int id, String nome, String habilidade, double forca, int idade, String reino, boolean protagonista) {
        this.id = id;
        this.nome = nome;
        this.habilidade = habilidade;
        this.forca = forca;
        this.idade = idade;
        this.reino = reino;
        this.protagonista = protagonista;
    }

    // Getters e Setters para cada atributo

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

    public String getHabilidade() {
        return habilidade;
    }

    public void setHabilidade(String habilidade) {
        this.habilidade = habilidade;
    }

    public double getForca() {
        return forca;
    }

    public void setForca(double forca) {
        this.forca = forca;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getReino() {
        return reino;
    }

    public void setReino(String reino) {
        this.reino = reino;
    }

    public boolean isProtagonista() {
        return protagonista;
    }

    public void setProtagonista(boolean protagonista) {
        this.protagonista = protagonista;
    }

    @Override
    public String toString() {
        return "Personagem [id=" + id + ", nome=" + nome + ", habilidade=" + habilidade + ", forca=" + forca
                + ", idade=" + idade + ", reino=" + reino + ", protagonista=" + protagonista + "]";
    }
}
