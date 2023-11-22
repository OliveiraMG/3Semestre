package org.example;

public class ListaEstatica {
    public int[] vetorNumero;
    public int numeroElementos; // Representa a quantidade de elementos que estão no vetor

    public ListaEstatica(int tamanho) {
        vetorNumero = new int[tamanho];
        numeroElementos = 0;
    }

    public void adicionar(int numero) {
        if (numeroElementos < vetorNumero.length) {
            vetorNumero[numeroElementos++] = numero;
        } else {
            System.out.println("Lista cheia!");
        }
    }

    public void exibir() {
        if (numeroElementos > 0) {
            System.out.print(vetorNumero[0]);

            for (int i = 1; i < numeroElementos; i++) {
                System.out.print(", " + vetorNumero[i]);
            }
        }
    }

    public int buscar(int numero) {
        for (int i = 0; i < numeroElementos; i++) {
            if (vetorNumero[i] == numero) {
                System.out.println("Número encontrado na posição " + i);
                return i;
            }
        }

        System.out.println("Número não encontrado!");
        return -1;
    }

    public boolean removePeloIndice(int indice) {
        if (indice >= 0 && indice < numeroElementos) {
            for (int i = indice; i < numeroElementos - 1; i++) {
                vetorNumero[i] = vetorNumero[i + 1];
            }

            numeroElementos--;
            return true;
        }

        return false;
    }

    public boolean removePeloValor(int numero) {
        return removePeloIndice(buscar(numero));
    }

    public boolean substituiValor(int valorAntigo, int valorNovo) {
        int indice = buscar(valorAntigo);

        if (indice != -1) {
            vetorNumero[indice] = valorNovo;
            System.out.println("Valor substituído!");
            return true;
        }

        System.out.println("Valor não encontrado!");
        return false;
    }

    public int contaOcorrencias(int valor) {
        int contador = 0;

        for (int i = 0; i < numeroElementos; i++) {
            if (vetorNumero[i] == valor) {
                contador++;
            }
        }

        System.out.println("O valor " + valor + " aparece " + contador + " vezes na lista.");
        return contador;
    }

    public boolean adicionaNoInicio(int valorAdicionado) {
        if (numeroElementos < vetorNumero.length) {
            for (int i = numeroElementos; i > 0; i--) {
                vetorNumero[i] = vetorNumero[i - 1];
            }

            vetorNumero[0] = valorAdicionado;
            numeroElementos++;
            return true;
        }

        System.out.println("Lista cheia!");
        return false;
    }

    public void adicionaNoIndice(int indice, int valorAdicionado) {
        if (indice < 0 || indice > numeroElementos) {
            System.out.println("Indice inválido!");
        } else if (numeroElementos >= vetorNumero.length) {
            System.out.println("Lista cheia!");
        } else {
            for (int i = numeroElementos - 1; i>= indice; i--) {
                vetorNumero[i + 1] = vetorNumero[i];
            }
            vetorNumero[indice] = valorAdicionado;
            numeroElementos++;
        }
    }

    public void ordena() {
        for (int i = 0; i < numeroElementos - 1; i++) {
            for (int j = 1; j < numeroElementos - i; j++) {
                if (vetorNumero[j - 1] > vetorNumero[j]) {
                    int aux = vetorNumero[j - 1];
                    vetorNumero[j - 1] = vetorNumero[j];
                    vetorNumero[j] = aux;
                }
            }
        }
    }

    public int pesquisaBinaria(int valor) {
        int indInf = 0;
        int indSup = numeroElementos - 1;
        int meio;

        while (indInf <= indSup) {
            meio = (indInf + indSup) / 2;
            if (valor == vetorNumero[meio]) {
                return meio;
            } else if (valor > vetorNumero[meio]) {
                indInf = meio + 1;
            } else {
                indSup = meio - 1;
            }
        }
        return -1;
    }
}

