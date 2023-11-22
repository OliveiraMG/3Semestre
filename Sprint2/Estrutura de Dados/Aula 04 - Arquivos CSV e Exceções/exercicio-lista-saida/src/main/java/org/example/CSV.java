package org.example;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Formatter;
import java.util.FormatterClosedException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class CSV {
    public void gravaArquivoCsv(ListaObj<PersonagemLivro> l, String nomeArq){
        FileWriter arq = null;
        Formatter saida = null;
        Boolean deuRuim = false;

        nomeArq += ".csv";

        try {
            arq = new FileWriter(nomeArq, false);
            saida = new Formatter(arq);
        }
        catch (IOException erro) {
            System.out.println("Erro ao abrir o arquivo");
            System.exit(1);
        }

        try {
            for (int i = 0; i < l.getTamanho(); i++) {
                PersonagemLivro personagem = l.getElemento(i);
                saida.format("%d;%s;%s;%.2f;%d;%s;%b\n",  // Usar %b para o tipo booleano
                        personagem.getId(), personagem.getNome(), personagem.getHabilidade(), personagem.getForca(),
                        personagem.getIdade(), personagem.getReino(), personagem.isProtagonista());
            }
        }
        catch (FormatterClosedException erro) {
            System.out.println("Erro ao gravar o arquivo");
            erro.printStackTrace();
            deuRuim = true;
        }
        finally {
            saida.close();
            try {
                arq.close();
            }
            catch (IOException erro) {
                System.out.println("Erro ao fechar o arquivo");
                erro.printStackTrace();
                deuRuim = true;
            }
        }
    }

    public void leExibeArquivoCsv(String nomeArq) {
        FileReader arq = null;
        Scanner entrada = null;
        Boolean deuRuim = false;

        nomeArq += ".csv";

        try {
            arq = new FileReader(nomeArq);
            entrada = new Scanner(arq).useDelimiter(";|\\n");
        }
        catch (FileNotFoundException erro) {
            System.out.println("Arquivo inexistente");
            System.exit(1);
        }

        try {
            System.out.printf("%-4S %-15S %-15S %-6S %-4S %-15S %11S\n", "id", "nome", "habilidade", "forca", "idade", "reino", "protagonista");
            while (entrada.hasNext()) {
                int id = entrada.nextInt();
                String nome = entrada.next();
                String habilidade = entrada.next();
                double forca = entrada.nextDouble();
                int idade = entrada.nextInt();
                String reino = entrada.next();
                boolean protagonista = entrada.nextBoolean();

                System.out.printf("%04d %-15s %-15s %6.2f %4d %-15s %11b\n", id, nome, habilidade, forca, idade, reino, protagonista);
            }
        }
        catch (NoSuchElementException erro) {
            System.out.println("Arquivo com problemas");
            erro.printStackTrace();
            deuRuim = true;
        }
        catch (IllegalStateException erro) {
            System.out.println("Erro na leitura do arquivo");
            erro.printStackTrace();
            deuRuim = true;
        }
        finally {
            entrada.close();
            try {
                arq.close();
            }
            catch (IOException erro) {
                System.out.println("Erro ao fechar o arquivo");
                erro.printStackTrace();
                deuRuim = true;
            }
        }
    }

}
