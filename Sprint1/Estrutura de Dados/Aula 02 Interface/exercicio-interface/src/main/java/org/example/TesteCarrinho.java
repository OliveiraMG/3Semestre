package org.example;

import java.util.Scanner;

public class TesteCarrinho {
    public static void main(String[] args) {
        Scanner leitor = new Scanner(System.in);
        Scanner leitorNextLine = new Scanner(System.in);
        Integer opcaoEscolhida;

        Carrinho carrinho = new Carrinho();

        do{
            System.out.println("Escolha uma das opções abaixo:\n"
                    + "1 - Adicionar livro\n"
                    + "2 - Adicionar DVD\n"
                    + "3 - Adicionar Servico\n"
                    + "4 - Exibir itens do carrinho\n"
                    + "5 - Exibir total de venda\n"
                    + "6 - Sair"
            );

            opcaoEscolhida = leitor.nextInt();

            switch (opcaoEscolhida) {
                case 0:
                    System.out.println("Até logo!");
                    break;

                default:
                    if (opcaoEscolhida > 6 || opcaoEscolhida < 1) {
                        System.out.println("Opção inválida!");
                    }
                    break;
            }

            switch(opcaoEscolhida) {
                case 1:
                    System.out.println("Digite o código do livro:");
                    Integer codigoLivro = leitor.nextInt();
                    System.out.println("Digite o valor do livro:");
                    Double valorLivro = leitor.nextDouble();
                    System.out.println("Digite o nome do livro:");
                    String nomeLivro = leitorNextLine.nextLine();
                    System.out.println("Digite o autor do livro:");
                    String autorLivro = leitorNextLine.nextLine();
                    System.out.println("Digite o isbn do livro:");
                    String isbnLivro = leitorNextLine.nextLine();

                    Livro livro = new Livro(codigoLivro, valorLivro, nomeLivro, autorLivro, isbnLivro);
                    carrinho.addVendavel(livro);
                    break;

                case 2:
                    System.out.println("Digite o código do DVD:");
                    Integer codigoDVD = leitor.nextInt();
                    System.out.println("Digite o valor do DVD:");
                    Double valorDVD = leitor.nextDouble();
                    System.out.println("Digite o nome do DVD:");
                    String nomeDVD = leitorNextLine.nextLine();
                    System.out.println("Digite a gravadora do DVD:");
                    String gravadoraDVD = leitorNextLine.nextLine();

                    Dvd dvd = new Dvd(codigoDVD, valorDVD, nomeDVD, gravadoraDVD);
                    carrinho.addVendavel(dvd);
                    break;

                case 3:
                    System.out.println("Digite a descrição do serviço:");
                    String descricaoServico = leitorNextLine.nextLine();
                    System.out.println("Digite o código do serviço:");
                    Integer codigoServico = leitor.nextInt();
                    System.out.println("Digite a quantidade de horas do serviço:");
                    Integer qtdHrsServico = leitor.nextInt();
                    System.out.println("Digite o valor da hora do serviço:");
                    Double valorHoraServico = leitor.nextDouble();

                    Servico servico = new Servico(descricaoServico, codigoServico, qtdHrsServico, valorHoraServico);
                    carrinho.addVendavel(servico);
                    break;

                case 4:
                    if (carrinho.getListaVendaveis().isEmpty()) {
                        System.out.println("Carrinho vazio!");
                    } else {
                        carrinho.exibeItensCarrinho();
                        break;
                    }

                case 5:
                    if (carrinho.getListaVendaveis().isEmpty()) {
                        System.out.println("Carrinho vazio!");
                    } else {
                        carrinho.calculaTotalVenda();
                        break;
                    }

            }
        } while (opcaoEscolhida != 6);

    }
}