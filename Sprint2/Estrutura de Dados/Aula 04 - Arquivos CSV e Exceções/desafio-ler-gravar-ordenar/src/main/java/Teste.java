import java.util.Scanner;

public class Teste {
    public static void main(String[] args) {
        ListaObj<Heroi> herois = new ListaObj<>(10);

        herois.adiciona(new Heroi(1, "Superman", "Super Força", "Criptonita", 10.0));
        herois.adiciona(new Heroi(2, "Batman", "Inteligência", "Humano", 9.0));
        herois.adiciona(new Heroi(3, "Mulher Maravilha", "Super Força", "Humano", 8.0));
        herois.adiciona(new Heroi(4, "Flash", "Super Velocidade", "Humano", 7.0));
        herois.adiciona(new Heroi(5, "Aquaman", "Super Força", "Humano", 6.0));

        Scanner leitor = new Scanner(System.in);

        while (true){
            System.out.println("""
                Escolha uma opção:
                1) Gravar Arquivo
                2) Ler Arquivo
                3) Ordenar por Força
                4) Remover Heróis com 3 ou mais de nível de força
                5) Gravar arquivo TXT
                6) Ler arquivo TXT
                7) Sair
                """);
            Integer opcaoEscolhida = leitor.nextInt();
            switch (opcaoEscolhida){
                case 1:
                    System.out.println("Gravando arquivo....");
                    GerenciadorDeArquivo.gravaArquivoCsv(herois, "herois");
                    break;
                case 2:
                    System.out.println("Lendo arquivo....");
                    GerenciadorDeArquivo.leArquivoCsv( "herois");
                    break;
                case 3:
                    for (int i = 0; i < herois.getTamanho() - 1; i++) {
                        int indMenor = i;
                        for (int j =  i + 1; j < herois.getTamanho(); j++) {
                            if(herois.getElemento(j).getNivelForca() < herois.getElemento(indMenor).getNivelForca()){
                                indMenor = j;
                            }
                        }
                        Heroi funci = herois.getElemento(i);
                        herois.substitui(i, herois.getElemento(indMenor));
                        herois.substitui(indMenor, funci);

                    }
                    break;
                case 4:
                    for (int i = 0; i < herois.getTamanho(); i++) {
                        Heroi h = herois.getElemento(i);
                        if (h.getNivelForca() >=3.0){
                            herois.removeElemento(h);
                        }
                    }
                    break;
                case 5:
                    System.out.println("Gravando arquivo TXT....");
                    GerenciadorDeArquivo.gravaArquivoTxt(herois, "herois.txt");
                    break;
                case 6:
                    System.out.println("Lendo arquivo TXT....");
                    GerenciadorDeArquivo.leArquivoTxt("herois.txt");
                    break;
                case 7:
                    System.out.println("Encerrando...");
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente!");
            }
        }


    }
}
