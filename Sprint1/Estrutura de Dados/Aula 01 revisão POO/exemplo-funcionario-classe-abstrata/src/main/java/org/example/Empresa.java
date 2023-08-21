package org.example;

import java.util.ArrayList;
import java.util.List;

public class Empresa {

    private List<Funcionario> funcionarios;

    public Empresa() {
        funcionarios = new ArrayList<>();
    }

    public void addFuncionario(Funcionario funcionario) {
        funcionarios.add(funcionario);
    }

    public void exibeTodos() {
        System.out.println("\nLista dos funcionários: \n");
        for (Funcionario funcionario : funcionarios) {
            System.out.println(funcionario);
        }
    }

    public void exibeSalarioTotal() {
        Double total = 0.0;
        for (Funcionario funcionario : funcionarios) {
            total += funcionario.calcSalario();
        }
        System.out.println("Total de salários: " + total);
    }

    public void exibeHoristas() {
        for (Funcionario funcionario : funcionarios) {
            if (funcionario instanceof Horista) {
                System.out.println(funcionario);
            }
        }
    }
}
