package org.example;

public class TestaTributo {

    public static void main(String[] args) {
        Perfume perfume = new Perfume(1, "212", 250.0, "Floral");
        Alimento alimento = new Alimento(1, "Salgadinho", 10.0, 20);
        Servico servico = new Servico("Consultoria", 190.0);
        Tributo tributo = new Tributo();
        tributo.addTributavel(alimento);
        tributo.addTributavel(perfume);
        tributo.addTributavel(servico);

        System.out.println("Total de tributos: " + tributo.calcularTributos());

        tributo.exibeTodos();
    }

}
