package org.example;

import java.util.Arrays;

public class PilhaObj <T>{

    // Atributos
    private T[] pilha;

    private int topo;

    // Construtor
    public PilhaObj(int capacidade) {
        pilha = (T[]) new Object[capacidade];
        topo = -1;
    }

    // Métodos
    // 03) MÃ©todo isEmpty
    public Boolean isEmpty() {
        return topo == -1;
    }

    // 04) MÃ©todo isFull
    public Boolean isFull() {
        return topo == pilha.length - 1;
    }

    // 05) MÃ©todo push
    public void push(T info) {
        if (isFull()) {
            throw new IllegalStateException("Pilha cheia");
        }

        pilha[++topo] = info;
    }

    // 06) MÃ©todo pop
    public T pop() {
        if (isEmpty()) {
            throw new RuntimeException("Pilha vazia");
        }

        return pilha[topo--];
    }

    // 07) MÃ©todo peek
    public T peek() {
        if (isEmpty()) {
            return null;
        }

        return pilha[topo];
    }

    // 08) MÃ©todo exibe
    public void exibe() {
        if (isEmpty()) {
            System.out.println("Pilha vazia");
        } else {
            System.out.print("Pilha: ");
            System.out.println(Arrays.toString(pilha));
            }
            System.out.println();
    }

    // 09) MÃ©todo isPalindromo
    public Boolean isPalindromo() {
        if (isEmpty()) {
            return false;
        }

        int i = 0;
        int j = topo;

        while (i < j) {
            if (pilha[i++] != pilha[j--]) {
                return false;
            }
        }

        return true;
    }

    // 10) MÃ©todo converteDecimalParaBinario
    public void converteDecimalParaBinario(int num) {
        // O tamanho da pilha nesse caso é feito assim por não haver o log na base dois,
        // então deve-se dividir o log do número que é recebido na base e (que seria o número de euler) pelo log na base 2 + 1

        Pilha pilha = new Pilha((int) (Math.log(num) / Math.log(2)) + 1);

        while (num > 0) {
            pilha.push(num % 2);
            num /= 2;
        }

        pilha.exibe();
    }

    //Getters & Setters (manter)
    public int getTopo() {
        return topo;
    }
}
