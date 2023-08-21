package org.example;

public class Main {
    public static void main(String[] args) {

        Engenheiro engenheiro = new Engenheiro("123456789-00", "João", 15000.0);
        Horista horista = new Horista("123456789-00", "Maria", 160, 10.0);
        Vendedor vendedor = new Vendedor("123456789-00", "José", 300.0, 0.10);

        System.out.println(engenheiro);
        System.out.println(horista);
        System.out.println(vendedor);

        Empresa empresa = new Empresa();

        empresa.addFuncionario(engenheiro);
        empresa.addFuncionario(horista);
        empresa.addFuncionario(vendedor);

        empresa.exibeTodos();
        empresa.exibeSalarioTotal();
        empresa.exibeHoristas();
    }
}