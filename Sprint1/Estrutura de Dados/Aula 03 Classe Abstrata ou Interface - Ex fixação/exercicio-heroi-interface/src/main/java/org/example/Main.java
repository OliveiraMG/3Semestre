package org.example;

public class Main {
    public static void main(String[] args) {
        EquipeHerois equipeHerois = new EquipeHerois();
        Heroi heroi1 = new Heroi("Homem de Ferro", 2000, 100, 100, 100, 6);
        Heroi heroi2 = new Heroi("Capitão América", 2200, 100, 100, 100, 6);
        Heroi heroi3 = new Heroi("Thor", 2500, 100, 100, 100, 6);

        Vilao vilao1 = new Vilao("Thanos", 5000, 100, 100, 100, 6);
        Vilao vilao2 = new Vilao("Loki", 1000, 100, 100, 100, 6);

        heroi1.treinar();
        heroi2.treinar();

        vilao1.treinar();
        vilao2.treinar();

        equipeHerois.adicionarHeroi(heroi1);
        equipeHerois.adicionarHeroi(heroi2);
        equipeHerois.adicionarHeroi(heroi3);
        vilao1.atacarOponente(heroi1);
        vilao2.atacarOponente(heroi2);
        equipeHerois.listarHerois();
    }
}