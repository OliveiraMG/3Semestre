package org.example;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Formatter;
import java.util.FormatterClosedException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class GerenciadorDeArquivo {
    public static void gravarArquivoCsv(ListaObj<Paciente> lista, String nomeArq) {
        FileWriter arq = null;
        Formatter saida = null;
        Boolean deuErrado = false;

        nomeArq += ".csv";

        try {
            arq= new FileWriter(nomeArq);
            saida = new Formatter(arq);
        } catch (IOException erro) {
            System.out.println("Erro ao abrir o arquivo");
            System.exit(1);
        }


        try {
            for (int i = 0; i < lista.getTamanho(); i++) {
                Paciente paciente = lista.getElemento(i);
                saida.format("%d;%s;%b;%s;%d;%.2f\n",paciente.getId(), paciente.getNome(), paciente.isPrioritario(),paciente.getSintomas(), paciente.getIdade(), paciente.getPeso());
            }
        } catch (FormatterClosedException erro) {
            System.out.println("Erro ao gravar o arquivo");
            deuErrado = true;
        } finally {
            saida.close();
            try {
                arq.close();
            } catch (IOException erro) {
                System.out.println("Erro ao fechar o arquivo");
                deuErrado = true;
            }
            if (deuErrado) {
                System.exit(1);
            }
        }
    }

    public static void leArquivoCsv(ListaObj<Paciente> lista, String nomeArq) {
        FileReader arq = null;
        Scanner entrada = null;
        Boolean deuErrado = false;

        nomeArq += ".csv";

        try {
            arq = new FileReader(nomeArq);
            entrada = new Scanner(arq).useDelimiter(";|\\n");
        } catch (FileNotFoundException erro) {
            System.out.println("Arquivo não encontrado");
            System.exit(1);
        }

        try{
            System.out.printf("%-4s | %-14s | %-11s | %-15s | %6s | %5s\n", "id", "Nome", "Prioritário", "Sintomas", "Idade", "Peso");
            while (entrada.hasNext()) {
                int id = entrada.nextInt();
                String nome = entrada.next();
                Boolean prioritario = entrada.nextBoolean();
                String sintomas = entrada.next();
                int idade = entrada.nextInt();
                double peso = entrada.nextDouble();

                System.out.printf("%-4d | %-14s | %-11b | %-15s | %6d | %5.2f\n", id, nome, prioritario, sintomas, idade, peso);
            }
        } catch (NoSuchElementException erro) {
            System.out.println("Arquivo com problemas");
            deuErrado = true;
        } catch (IllegalStateException erro) {
            System.out.println("Erro na leitura do arquivo");
            deuErrado = true;
        } finally {
            entrada.close();
            try {
                arq.close();
            } catch (IOException erro) {
                System.out.println("Erro ao fechar o arquivo");
                deuErrado = true;
            }
            if (deuErrado) {
                System.exit(1);
            }
        }
    }
}
