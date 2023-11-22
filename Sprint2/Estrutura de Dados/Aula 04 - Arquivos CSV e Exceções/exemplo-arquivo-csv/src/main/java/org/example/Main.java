package org.example;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Formatter;
import java.util.FormatterClosedException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {

    public static void gravaArquivoCsv(ListaObj<Cachorro> lista, String nomeArq) {
        FileWriter arq = null;   // representa o arquivo que sera gravado
        Formatter saida = null;  // obj saida sera usado para escrever no arquivo
        Boolean deuRuim = false;

        nomeArq += ".csv";   // acrescenta a extensao .csv ao nome do arquivo

        // Bloco try-catch para abrir o arquivo
        try {
            arq = new FileWriter(nomeArq);  // abre o arquivo
            saida = new Formatter(arq);     // instancia o obj saida, asssociando-o ao arq
        }
        catch (IOException erro) {
            System.out.println("Erro ao abrir o arquivo");
            System.exit(1);
        }

        // Bloco try-catch para gravar o arquivo
        try {
            // Percorre a lista de cachorros, obtendo um objeto por vez
            // e gravando um registro para cada objeto Cachorro
            for (int i=0; i < lista.getTamanho(); i++) {
                Cachorro dog = lista.getElemento(i);
                // grava os dados dos atributos de dog no arquivo
                // ATENCAO: NÃO ACRESCENTAR ESPACOS ENTRE OS VALORES
                // E TB NÃO USAR POR EXEMPLO %10d
                // TB NÃO COLOCAR UM PONTO E VÍRGULA ANTES DO \n
                saida.format("%d;%s;%s;%.2f\n", dog.getId(), dog.getNome(),
                        dog.getPorte(), dog.getPeso());
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
            if (deuRuim) {
                System.exit(1);
            }
        }
    }

    public static void leExibeArquivoCsv(String nomeArq) {
        FileReader arq = null;  // representa o arquivo a ser lido
        Scanner entrada= null;  // obj usado para ler do arquivo
        Boolean deuRuim = false;

        // Acrescenta a extensao .csv ao nome do arquivo
        nomeArq += ".csv";

        // Bloco try-catch para abrir o arquivo
        try {
            arq = new FileReader(nomeArq);
            entrada = new Scanner(arq).useDelimiter(";|\\n");
        }
        catch (FileNotFoundException erro) {
            System.out.println("Arquivo inexistente");
            System.exit(1);
        }

        // Bloco try-catch para ler o arquivo
        try {
            // Exibe a linha de títulos das colunas na console
            System.out.printf("%-4S %-15S %-9S %4S\n","id", "nome", "porte", "peso");

            // Loop para ler o arquivo
            // hasNext() retorna true enquanto tem linha para ler do arquivo
            // qdo ele encontra o fim de arquivo (EOF), o hasNext() retorna false
            while (entrada.hasNext()) {
                int id = entrada.nextInt();
                String nome = entrada.next();  // NÃO USAR nextLine()
                String porte = entrada.next();
                Double peso = entrada.nextDouble();

                // Exibe os dados de um cachorro usando saida formatada
                System.out.printf("%04d %-15s %-9s %4.1f\n", id, nome, porte, peso);
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
            if (deuRuim) {
                System.exit(1);
            }
        }
    }


    public static void main(String[] args) {
        ListaObj<Cachorro> listaDog = new ListaObj(7);

        // Preenchendo a lista de cachorros
        listaDog.adiciona(new Cachorro(1, "Patricio","Medio",12.0));
        listaDog.adiciona(new Cachorro(2, "Caramelo","Medio",9.5));
        listaDog.adiciona(new Cachorro(3, "Napoleao","Grande",55.0));
        listaDog.adiciona(new Cachorro(4, "Brenda","Grande",50.0));
        listaDog.adiciona(new Cachorro(5, "Claudio Raio","Medio",17.0));
        listaDog.adiciona(new Cachorro(6, "Trovao","Medio",22.0));
        listaDog.adiciona(new Cachorro(7, "Gigante","Pequeno",10.0));

        // Exibe a lista de cachorros
        System.out.println("Lista criada:");
        listaDog.exibe();

        // Grava o conteudo da lista num arquivo .csv
        gravaArquivoCsv(listaDog,"dogs");

        // Le e exibe os dados do arquivo dogs.csv
        System.out.println("\nDados lidos do arquivo:");
        leExibeArquivoCsv("dogs");
    }
}