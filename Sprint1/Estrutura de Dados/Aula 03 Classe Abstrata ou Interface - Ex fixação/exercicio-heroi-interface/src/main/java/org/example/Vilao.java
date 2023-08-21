package org.example;

public class Vilao extends BasePersonagemFicticio {
    private int nivelMaldade;

    public Vilao(String nome, int pontosVida, int forca, int ataque, int defesa, int nivelMaldade) {
        super(nome, pontosVida, forca, ataque, defesa);
        this.nivelMaldade = nivelMaldade;
    }

    public int getNivelMaldade() {
        return nivelMaldade;
    }

    public void setNivelMaldade(int nivelMaldade) {
        this.nivelMaldade = nivelMaldade;
    }

    public void treinar() {
        this.setForca(this.getForca() + 3);
        this.setDefesa(this.getDefesa() + 2);
        this.setPontosVida(this.getPontosVida() + 5);
        System.out.println("\nO vilão " + this.getNome() + " treinou e ficou com " + this.getForca() + " de força, " + this.getDefesa() + " de defesa e " + this.getPontosVida() + " de pontos de vida.\n");
    }

    public void atacarOponente(BasePersonagemFicticio alvo) {
        alvo.setPontosVida(alvo.getPontosVida() - (this.getAtaque() + this.getForca() + (this.getNivelMaldade() * 2)));
        System.out.println("\nO vilão " + this.getNome() + " atacou o herói " + alvo.getNome() + " com " + this.getAtaque() + " de ataque, " + this.getForca() + " de força. O herói " + alvo.getNome() + " ficou com " + alvo.getPontosVida() + " de pontos de vida.\n");;
    }

}
