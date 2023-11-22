package org.example;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ExemploThrows {

    public static int divide (int a, int b) throws ArithmeticException {
        return a / b;
    }

    public static void main(String[] args) {
        Scanner leitor = new Scanner(System.in);

        try {
            System.out.println("Digite um número");
            int numerador = leitor.nextInt();
            System.out.println("Digite outro número");
            int denominador = leitor.nextInt();
            System.out.println(numerador + "/" + denominador +
                    " = " + divide(numerador, denominador));
        }
        catch (ArithmeticException erro) {
            System.out.println("Divisão por zero!");
            erro.printStackTrace();
        }
        catch (InputMismatchException erro) {
            System.out.println("Digite apenas números!");
            erro.printStackTrace();
        }
    }
}
