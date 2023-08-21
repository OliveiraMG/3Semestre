package org.example;

public class Heroi extends BasePersonagemFicticio {

    private int nivelHeroismo;

    public Heroi(String nome, int pontosVida, int forca, int ataque, int defesa, int nivelHeroismo) {
        super(nome, pontosVida, forca, ataque, defesa);
        this.nivelHeroismo = nivelHeroismo;
    }

    public int getNivelHeroismo() {
        return nivelHeroismo;
    }

    public void setNivelHeroismo(int nivelHeroismo) {
        this.nivelHeroismo = nivelHeroismo;
    }

    public void treinar() {
        this.setForca(this.getForca() + 5);
        this.setAtaque(this.getAtaque() + 2);
        this.setPontosVida(this.getPontosVida() + 4);
        System.out.println("\nO herói " + this.getNome() + " treinou e ficou com " + this.getForca() + " de força, " + this.getAtaque() + " de ataque e " + this.getPontosVida() + " de pontos de vida.\n");
    }

    public void atacarOponente(BasePersonagemFicticio alvo) {
        alvo.setPontosVida(alvo.getPontosVida() - (this.getAtaque() + this.getForca() + (this.getNivelHeroismo() * 3)));
        System.out.println("\nO herói " + this.getNome() + " atacou o vilão " + alvo.getNome() + " com " + this.getAtaque() + " de ataque, " + this.getForca() + " de força. O vilão " + alvo.getNome() + " ficou com " + alvo.getPontosVida() + " de pontos de vida.\n");;
    }

}
