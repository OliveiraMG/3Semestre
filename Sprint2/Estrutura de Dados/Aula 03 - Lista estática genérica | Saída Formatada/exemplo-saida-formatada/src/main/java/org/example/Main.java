package org.example;

public class Main {
    public static void main(String[] args) {
        String nome1 = "Maria";
        String nome2 = "João";
        int ra1 = 50000;
        int ra2 = 50001;
        Double nota1 = 8.5;
        Double nota2 = 10.0;

        System.out.printf("-------------------------------------\n");
        System.out.printf("%-7S %-14S %7S\n", "ra", "nome", "nota");
        System.out.printf("%07d %-14s %7.2f\n", ra1, nome1, nota1);
        System.out.printf("%07d %-14s %7.2f\n", ra2, nome2, nota2);


    }
}