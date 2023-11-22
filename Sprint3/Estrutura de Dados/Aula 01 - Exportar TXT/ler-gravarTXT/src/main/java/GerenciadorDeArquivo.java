import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class GerenciadorDeArquivo {
    public static void gravaArquivoCsv(ListaObj<Heroi> lista, String nomeArq) {
        FileWriter arq = null;
        Formatter saida = null;
        Boolean deuRuim = false;

        nomeArq += ".csv";

        // Bloco try-catch para abrir o arquivo
        try {
            arq = new FileWriter(nomeArq);
            saida = new Formatter(arq);
        } catch (IOException erro) {
            System.out.println("Erro ao abrir o arquivo");
            System.exit(1);
        }

        // Bloco try-catch para gravar o arquivo
        try {
            for (int i = 0; i < lista.getTamanho(); i++) {
                Heroi heroi = lista.getElemento(i);
                saida.format("%d;%s;%s;%s;%.2f\n",
                        heroi.getId(), heroi.getNome(), heroi.getPoder(), heroi.getFraqueza(), heroi.getNivelForca());
            }
        } catch (FormatterClosedException erro) {
            System.out.println("Erro ao gravar o arquivo");
            deuRuim = true;
        } finally {
            saida.close();
            try {
                arq.close();
            } catch (IOException erro) {
                System.out.println("Erro ao fechar o arquivo");
                deuRuim = true;
            }
            if (deuRuim) {
                System.exit(1);
            }
        }
    }

    public static void leArquivoCsv(String nomeArq) {
        FileReader arq = null;
        Scanner entrada = null;
        Boolean deuRuim = false;

        nomeArq += ".csv";

        // Bloco try-catch para abrir o arquivo
        try {
            arq = new FileReader(nomeArq);
            entrada = new Scanner(arq).useDelimiter(";|\\n");
        } catch (FileNotFoundException erro) {
            System.out.println("Arquivo nao encontrado");
            System.exit(1);
        }

        // Bloco try-catch para ler o arquivo
        try {
            System.out.printf("%2s %20s %-30s %-10s %-10s\n",
                    "ID", "Nome", "Poder", "Fraqueza", "Nível Força");

            while (entrada.hasNext()) {
                Integer id = entrada.nextInt();
                String nome = entrada.next();
                String poder = entrada.next();
                String fraqueza = entrada.next();
                Double nivelForca = entrada.nextDouble();

                System.out.printf("%2d %20s %-30s %-10s %-10.2f\n",
                        id, nome, poder, fraqueza, nivelForca);
            }
        } catch (NoSuchElementException erro) {
            System.out.println("Arquivo com problemas");
            deuRuim = true;
        } catch (IllegalStateException erro) {
            System.out.println("Erro na leitura do arquivo");
            deuRuim = true;
        } finally {
            entrada.close();
            try {
                arq.close();
            } catch (IOException erro) {
                System.out.println("Erro ao fechar o arquivo");
                deuRuim = true;
            }
            if (deuRuim) {
                System.exit(1);
            }
        }
    }

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
        } catch (IOException e) {
            System.out.println("Erro ao gravar arquivo: ");
            e.printStackTrace();
        }

    }

    public static void gravaArquivoTxt(ListaObj<Heroi> lista, String nomeArq) {
        int contaRegistroDadosGravados = 0;

        // Monta o registro do header
        String header = "00HEROI";
        header += LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
        header += "01";

        // Grava o header no arquivo
        gravaRegistro(header, nomeArq);

        // Monta e grava os registros de dados (registro de corpo)
        for (int i = 0; i < lista.getTamanho(); i++) {
            Heroi heroi = lista.getElemento(i);
            String corpo = "02";

            corpo += String.format("%-5.5s", heroi.getId());
            corpo += String.format("%-8.8s", heroi.getNome());
            corpo += String.format("%-50.50s", heroi.getPoder());
            corpo += String.format("%-40.40s", heroi.getFraqueza());
            corpo += String.format("%05.2f", heroi.getNivelForca());

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
        String nome, poder, fraqueza;
        int id;
        Double nivelForca;
        int contaRegistroDadosLidos = 0;
        int qtdeRegistrosDadosGravados = 0;

        // Cria uma lista lida para armazenar os dados lidos do arquivo para simular a importação dos dados para o banco de dados
        ListaObj<Heroi> listaLida = new ListaObj<>(10);
        // Bloco try-catch para abrir o arquivo
        try {
            entrada = new BufferedReader(new FileReader(nomeArq));
        }
        catch (IOException e) {
            System.out.println("Arquivo não encontrado: " + e.getMessage());
        }

        // Bloco try-catch para ler o arquivo e fechar o arquivo
        try {
            registro = entrada.readLine();
            while (registro != null) {
                tipoRegistro = registro.substring(0, 2);

                if (tipoRegistro.equals("00")) {
                    System.out.println("Tipo de arquivo: " + registro.substring(2, 7));
                    System.out.println("Data e hora da criação do arquivo: " + registro.substring(7, 26));
                    System.out.println("Versão do arquivo: " + registro.substring(26, 28));
                } else if (tipoRegistro.equals("01")) {
                    System.out.println("Trailer: " + registro);
                    qtdeRegistrosDadosGravados = Integer.parseInt(registro.substring(2, 12));
                    if (qtdeRegistrosDadosGravados == contaRegistroDadosLidos) {
                        System.out.println("Quantidade de registros de dados gravados é compatível com a quantidade de registros de dados lidos");
                    } else {
                        System.out.println("Quantidade de registros de dados gravados é incompatível com a quantidade de registros de dados lidos");
                    }
                }
                else if (tipoRegistro.equals("02")) {
                    id = Integer.parseInt(registro.substring(2, 7).trim());
                    nome = registro.substring(7, 15).trim();
                    poder = registro.substring(15, 65).trim();
                    fraqueza = registro.substring(65, 105).trim();
                    nivelForca = Double.parseDouble(registro.substring(105, 110).replace(',', '.'));

                    // Contabiliza que leu mais um registro de dados
                    contaRegistroDadosLidos++;

                    // Criar o objeto heroi e adicionar na lista lida
                    Heroi heroi = new Heroi(id, nome, poder, fraqueza, nivelForca);
                    listaLida.adiciona(heroi);
                }
                else {
                    System.out.println("Tipo de registro desconhecido: " + registro);
                }
                registro = entrada.readLine();
            }
            entrada.close();
        }
        catch (IOException e) {
            System.out.println("Erro ao ler arquivo: " + e.getMessage());
        }

        // Exibe a lista de heróis lida do arquivo
        System.out.println("Lista de heróis lida do arquivo:");
        for (int i = 0; i < listaLida.getTamanho(); i++) {
            Heroi heroi = listaLida.getElemento(i);
            System.out.println(heroi);
        }
    }
}
