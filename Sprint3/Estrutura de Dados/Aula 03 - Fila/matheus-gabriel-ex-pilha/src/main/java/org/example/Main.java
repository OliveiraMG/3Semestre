package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        List<Heroi> lista = new ArrayList<>();
        PilhaObj<Integer> pilha = new PilhaObj<>(10);

        Repositorio repositorio = new Repositorio(lista, pilha);

        int opcao = 0;

        while (opcao != 7) {
            System.out.println("1- Salvar Herói");
            System.out.println("2- Deletar Herói");
            System.out.println("3- Exibir");
            System.out.println("4- Desfazer");
            System.out.println("5- Agendar Salvar");
            System.out.println("6- Executar agendado");
            System.out.println("7- Fim");

            Scanner scanner = new Scanner(System.in);

            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    System.out.println("Digite o id do herói, deve ser maior que 100");
                    int id = scanner.nextInt();
                    if (id < 100) {
                        System.out.println("O id deve ser maior que 100");
                        break;
                    }

                    System.out.println("Digite o nome do herói");
                    String nome = scanner.next();
                    System.out.println("Digite o poder do herói");
                    String poder = scanner.next();
                    System.out.println("Digite a fraqueza do herói");
                    String fraqueza = scanner.next();
                    System.out.println("Digite a quantidade de força do herói");
                    Double forca = scanner.nextDouble();

                    Heroi heroi = new Heroi(id, nome, poder, fraqueza, forca);

                    repositorio.salvar(heroi);

                    break;
                case 2:
                    System.out.println("Digite o id do herói a ser deletado");
                    int idDeletar = scanner.nextInt();
                    repositorio.deletar(idDeletar);
                    break;
                case 3:
                    repositorio.exibir();
                    break;
                case 4:
                    repositorio.desfazer();
                    break;
                case 5:
                    System.out.println("Digite o id do herói, deve ser maior que 100");
                    int idAgendar = scanner.nextInt();
                    if (idAgendar < 100) {
                        System.out.println("O id deve ser maior que 100");
                        break;
                    }

                    System.out.println("Digite o nome do herói");
                    String nomeAgendar = scanner.next();
                    System.out.println("Digite o poder do herói");
                    String poderAgendar = scanner.next();
                    System.out.println("Digite a fraqueza do herói");
                    String fraquezaAgendar = scanner.next();
                    System.out.println("Digite a quantidade de força do herói");
                    Double forcaAgendar = scanner.nextDouble();

                    Heroi heroiAgendar = new Heroi(idAgendar, nomeAgendar, poderAgendar, fraquezaAgendar, forcaAgendar);

                    repositorio.agendarSalvar(heroiAgendar);
                    break;
                case 6:
                    System.out.println("Digite a quantidade de operações agendadas a serem executadas");
                    int quantidade = scanner.nextInt();
                    repositorio.executarAgendado(quantidade);
                    break;
                case 7:
                    System.out.println("Fim");
                    break;
                default:
                    System.out.println("Opção inválida");
                    break;
            }


        }
    }
}