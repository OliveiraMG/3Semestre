package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ListaObj<Paciente> pacientes = new ListaObj<>(6);

        pacientes.adiciona(new Paciente(11, "Ana Teixeira",false,"Dor de cabeca", 30, 56.0));
        pacientes.adiciona(new Paciente(12, "Mario Silva",true,"Febre alta", 63, 70.5));
        pacientes.adiciona(new Paciente(13, "Catia Miranda",false,"Dor na lombar", 45, 61.4));
        pacientes.adiciona(new Paciente(14, "Fabio Reis",false,"Dor de garganta", 25, 65.7));
        pacientes.adiciona(new Paciente(15, "Paula Souza",true,"Gestante", 27, 57.7));
        pacientes.adiciona(new Paciente(16, "Lauro Macedo",true,"Pedra no rim", 65, 68.3));
        pacientes.adiciona(new Paciente(17, "Rita Moura",false,"Dor de cabeca", 35, 60.0));

        Scanner leitor = new Scanner(System.in);

        while (true) {
            System.out.println("""
                    Escolha uma opção:
                    1) Gravar Arquivo
                    2) Ler Arquivo
                    3) Sair
                    """);
            Integer opcaoEscolhida = leitor.nextInt();
            switch (opcaoEscolhida) {
                case 1:
                    System.out.println("Gravando arquivo....");
                    GerenciadorDeArquivo.gravarArquivoCsv(pacientes, "paciente");
                    break;
                case 2:
                    System.out.println("Lendo arquivo....");
                    GerenciadorDeArquivo.leArquivoCsv(pacientes, "paciente");
                    break;
                case 3:
                    System.out.println("Saindo....");
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }
}