package org.example;

public abstract class BasePersonagemFicticio implements PersonagemQuadrinhos{
    private String nome;
    private int pontosVida;
    private int forca;
    private int ataque;
    private int defesa;

    public BasePersonagemFicticio(String nome, int pontosVida, int forca, int ataque, int defesa) {
        this.nome = nome;
        this.pontosVida = pontosVida;
        this.forca = forca;
        this.ataque = ataque;
        this.defesa = defesa;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getPontosVida() {
        return pontosVida;
    }

    public void setPontosVida(int pontosVida) {
        this.pontosVida = pontosVida;
    }

    public int getForca() {
        return forca;
    }

    public void setForca(int forca) {
        this.forca = forca;
    }

    public int getAtaque() {
        return ataque;
    }

    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }

    public int getDefesa() {
        return defesa;
    }

    public void setDefesa(int defesa) {
        this.defesa = defesa;
    }

    public abstract void atacarOponente(BasePersonagemFicticio alvo);
}
