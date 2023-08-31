package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Exercício 05

        String[] nomesCarros = new String[5];
        double[] rendimentosCarros = new double[5];

        Scanner leitorCarros = new Scanner(System.in);
        Scanner leitorRendimento = new Scanner(System.in);

        for (int i = 0; i < nomesCarros.length; i++) {
            System.out.println("Digite o nome do carro " + (i + 1));
            nomesCarros[i] = leitorCarros.nextLine();
        }

        for (int i = 0; i < rendimentosCarros.length; i++) {
            System.out.println("Digite o rendimento do carro " + (i + 1));
            rendimentosCarros[i] = leitorRendimento.nextDouble();
        }


        double maiorRendimento = 0;
        String nomeMaiorRendimento = "";

        for (int i = 0; i < rendimentosCarros.length; i++) {
            if (rendimentosCarros[i] > maiorRendimento) {
                maiorRendimento = rendimentosCarros[i];
                nomeMaiorRendimento = nomesCarros[i];
            }
        }


        System.out.println("O maior rendimento é do carro: " + nomeMaiorRendimento + " com " + maiorRendimento + " km/l");

        // Exercício 06

        Scanner leitorDia = new Scanner(System.in);
        Scanner leitorMes = new Scanner(System.in);

        System.out.println("Digite o dia: ");
        int dia = leitorDia.nextInt();

        System.out.println("Digite o mês: ");
        int mes = leitorMes.nextInt();

        int[] diasMes = {31,28,31,30,31,30,31,31,30,31,30,31};

        int diasTotais = 0;

        if (mes < 1 || mes > 12) {
            System.out.println("Mês inválido. Insira um valor entre 1 e 12.");
            return;
        }

        if (dia < 1 || dia > diasMes[mes - 1]) {
            System.out.println("Dia inválido para o mês fornecido.");
            return;
        }


        for (int i = 0; i < mes - 1; i++) {
                diasTotais += diasMes[i];
            }


        diasTotais += dia;

        System.out.println("O dia " + dia + " do mês " + mes + " é o dia " + diasTotais + " do ano.");

    }
}