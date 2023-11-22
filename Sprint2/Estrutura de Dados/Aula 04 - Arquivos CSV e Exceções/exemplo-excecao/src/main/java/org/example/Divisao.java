package org.example;

import javax.swing.*;

public class Divisao {

    public static void main(String[] args) {

        String snum1, snum2;
        int num1, num2;

        snum1 = JOptionPane.showInputDialog("Digite um número:");
        snum2 = JOptionPane.showInputDialog("Digite outro número:");

        try {
            num1 = Integer.parseInt(snum1);
            num2 = Integer.parseInt(snum2);

            JOptionPane.showMessageDialog(null,
                    num1 / num2);
        }
        catch (ArithmeticException erro) {
            JOptionPane.showMessageDialog(null,
                    "Divisão por zero!\n" + erro,
                    "ERRO",
                    JOptionPane.ERROR_MESSAGE);
        }
        catch (NumberFormatException erro) {
            JOptionPane.showMessageDialog(null,
                    "Digite apenas números!\n" + erro,
                    "ERRO",
                    JOptionPane.ERROR_MESSAGE);
        }

        System.exit(0);

    }


}
