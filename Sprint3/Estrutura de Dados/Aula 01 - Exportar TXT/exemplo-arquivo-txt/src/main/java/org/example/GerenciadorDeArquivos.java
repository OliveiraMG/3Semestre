package org.example;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class GerenciadorDeArquivos {

    public static void gravaRegistro(String registro, String nomeArq) {
        BufferedWriter saida = null;

        // Bloco try-catch para abrir o arquivo
        try {
            saida = new BufferedWriter(new FileWriter(nomeArq, true));
        }
        catch (IOException e) {
            System.out.println("Erro ao abrir arquivo: " + e.getMessage());
        }

        // Bloco try-catch para gravar o registro e fechar o arquivo
        try {
            saida.append(registro + "\n");
            saida.close();
        }
        catch (IOException e) {
            System.out.println("Erro ao gravar arquivo: ");
            e.printStackTrace();
        }
    }

    public static void gravaArquivoTxt(List<Aluno> lista, String nomeArq) {
        int contaRegistroDadosGravados = 0;

        // Monta o registro do header
        String header = "00NOTA20232";
        header += LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
        header += "01";

        // Grava o header no arquivo
        gravaRegistro(header, nomeArq);

        // Monta e grava os registros de dados (registro de corpo)
        for (Aluno aluno : lista) {
            String corpo = "02";

            corpo += String.format("%-5.5s", aluno.getCurso());
            corpo += String.format("%-8.8s", aluno.getRa());
            corpo += String.format("%-50.50s", aluno.getNome());
            corpo += String.format("%-40.40s", aluno.getNomeDaDisciplina());
            corpo += String.format("%05.2f", aluno.getMediaFinal());
            corpo += String.format("%03d", aluno.getQuantidadeDeFaltas());

            // Grava o registro de corpo no arquivo
            gravaRegistro(corpo, nomeArq);
            // Contabiliza a quantidade de registros de dados gravados
            contaRegistroDadosGravados++;
        }

        // Monta o registro de trailer
        String trailer = "01";
        trailer += String.format("%010d", contaRegistroDadosGravados);

        // Grava o registro de trailer no arquivo
        gravaRegistro(trailer, nomeArq);

    }

    public static void leArquivoTxt(String nomeArq) {
        BufferedReader entrada = null;
        String registro, tipoRegistro;
        String ra, nome, curso, disciplina;
        Double mediaFinal;
        int qtdFaltas;
        int contaRegistroDadosLidos = 0;
        int qtdRegistroDadosGravados = 0;

        // Cria uma lista lida para armazenar os dados lidos do arquivo para simular a importação dos dados para o banco de dados
        List<Aluno> listaLida = new ArrayList<>();
        // Bloco try-catch para abrir o arquivo
        try {
            entrada = new BufferedReader(new FileReader(nomeArq));
        }
        catch (IOException e) {
            System.out.println("Erro ao abrir arquivo: ");
        }

        // Bloco try-catch para ler o arquivo e fechar o arquivo
        try {
            registro = entrada.readLine();
            while (registro != null) {
                tipoRegistro = registro.substring(0, 2);

                if (tipoRegistro.equals("00")) {
                    // Header
                    System.out.println("Tipo de arquivo: " + registro.substring(2, 6));
                    System.out.println("Ano e semestre: " + registro.substring(6, 11));
                    System.out.println("Data e hora de geração do arquivo: " + registro.substring(11, 30));
                    System.out.println("Versão do arquivo: " + registro.substring(30, 32));
                }
                else if (tipoRegistro.equals("01")) {
                    // Trailer
                    System.out.println("Trailer: " + registro);
                    qtdRegistroDadosGravados = Integer.parseInt(registro.substring(2, 12));
                    if (qtdRegistroDadosGravados == contaRegistroDadosLidos) {
                        System.out.println("Quantidade de registros de dados gravados é compatível com a quantidade de registros de dados lidos.");
                    }
                    else {
                        System.out.println("Quantidade de registros de dados gravados é incompatível com a quantidade de registros de dados lidos.");
                    }
                }
                else if (tipoRegistro.equals("02")) {
                    curso = registro.substring(2, 7).trim();
                    ra = registro.substring(7, 15).trim();
                    nome = registro.substring(15, 65).trim();
                    disciplina = registro.substring(65, 105).trim();
                    mediaFinal = Double.parseDouble(registro.substring(105, 110).replace(',', '.'));
                    qtdFaltas = Integer.parseInt(registro.substring(110, 113));

                    // Contabiliza que leu mais um registro de dados
                    contaRegistroDadosLidos++;

                    //  Criar o objeto aluno com os dados lidos do arquivo
                    Aluno aluno = new Aluno(ra, nome, curso, disciplina, mediaFinal, qtdFaltas);

                    // Para importar esses dados podemos fazer
                    // repository.save(aluno);

                    // No nosso caso como não estamos conectados a banco de dados, a gente vai adicionar o aluno em uma lista
                    // vamos add o aluno na lista lista lida
                    listaLida.add(aluno);
                }
                else {
                    System.out.println("Tipo de registro inválido: " + tipoRegistro);
                }

                registro = entrada.readLine();
            }
            entrada.close();
        } catch (IOException e) {
            System.out.println("Erro ao ler arquivo: ");
            e.printStackTrace();
        }

        // Exibe a lista de alunos lidos do arquivo
        System.out.println("Lista de alunos lidos do arquivo:");
        for (Aluno aluno : listaLida) {
            System.out.println(aluno);
        }

    }

}