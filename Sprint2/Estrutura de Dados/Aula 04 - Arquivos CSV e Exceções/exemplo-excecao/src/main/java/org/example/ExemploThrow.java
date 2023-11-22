package org.example;

import java.util.Scanner;

public class ExemploThrow {

    public static void main(String[] args) {
        Scanner leitor = new Scanner(System.in);

        try {
            System.out.println("Digite a nota1 (entre 0 e 10)");
            Double nota1 = leitor.nextDouble();
            if (nota1 < 0 || nota1 > 10) {
                throw new LimiteUltrapassadoException("Valor inválido da nota1");
            }
            System.out.println("Digite a nota2 (entre 0 e 10)");
            Double nota2 = leitor.nextDouble();
            if (nota2 < 0 || nota2 > 10) {
                throw new LimiteUltrapassadoException("Valor inválido da nota2");
            }
            System.out.println("Média = " + (nota1+nota2)/2);
        }
        catch (LimiteUltrapassadoException erro) {
            System.out.println(erro + "-" + erro.dataHora);
            erro.printStackTrace();
        }
    }
}
