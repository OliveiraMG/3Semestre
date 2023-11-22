package org.example;

import java.util.ArrayList;
import java.util.List;

import static org.example.GerenciadorDeArquivos.gravaArquivoTxt;
import static org.example.GerenciadorDeArquivos.leArquivoTxt;

public class Main {
    public static void main(String[] args) {
        List<Aluno> lista = new ArrayList<>();

        lista.add(new Aluno("01222013", "João", "ADS", "Estrutura de dados", 8.0, 20));
        lista.add(new Aluno("01222014", "Maria", "ADS", "Estrutura de dados", 9.0, 20));
        lista.add(new Aluno("01222015", "José", "ADS", "Estrutura de dados", 10.0, 10));
        lista.add(new Aluno("01222016", "Ana", "ADS", "Estrutura de dados", 7.0, 15));
        lista.add(new Aluno("01222017", "Carlos", "ADS", "Estrutura de dados", 6.0, 10));

        //Exibe a lista de alunos
        System.out.println("Lista de alunos:");
        for (Aluno aluno : lista) {
            System.out.println(aluno);
        }

//        gravaArquivoTxt(lista, "alunos.txt");


        // Le arquivo TXT
        leArquivoTxt("alunos.txt");
    }

}