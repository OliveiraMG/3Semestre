package org.example;

import org.example.Heroi;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<Heroi> lista = new ArrayList<>(5);
        Scanner scan = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\nEscolha a opção desejada: (Digite 1, 2 ou 3)");
            System.out.println("1 - Adicionar um Herói");
            System.out.println("2 - Exibir Relatório");
            System.out.println("3 - Sair");
            opcao = scan.nextInt();

            switch (opcao) {
                case 1 -> {
                    // Código para adicionar um herói
                    System.out.println("Digite o ID do Herói:");
                    int id = scan.nextInt();
                    System.out.println("Digite o nome do Herói:");
                    String nome = scan.next();
                    System.out.println("Digite o poder do Herói:");
                    String poder = scan.next();
                    System.out.println("Digite a fraqueza do Herói:");
                    String fraqueza = scan.next();
                    System.out.println("Digite a altura do Herói:");
                    double altura = scan.nextDouble();
                    System.out.println("Digite o peso do Herói:");
                    double peso = scan.nextDouble();
                    System.out.println("O Herói tem capa? (true/false):");
                    boolean temCapa = scan.nextBoolean();

                    // Crie uma instância do Herói com os dados fornecidos pelo usuário
                    Heroi heroi = new Heroi(id, nome, poder, fraqueza, altura, peso, temCapa);

                    // Adicione o herói à lista
                    lista.add(heroi);
                    System.out.println("Herói adicionado com sucesso!");
                }
                case 2 -> {
                    // Código para exibir relatório
                    System.out.println("Relatório de Heróis:");
                    for (Heroi heroi : lista) {
                        System.out.println("ID: " + heroi.getId());
                        System.out.println("Nome: " + heroi.getNome());
                        System.out.println("Poder: " + heroi.getPoder());
                        System.out.println("Fraqueza: " + heroi.getFraqueza());
                        System.out.println("Altura: " + heroi.getAltura());
                        System.out.println("Peso: " + heroi.getPeso());
                        System.out.println("Tem Capa: " + heroi.isTemCapa());
                        System.out.println("-------------");
                    }
                }
                case 3 -> System.out.println("Encerrando o programa...");
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 3);
    }
}
