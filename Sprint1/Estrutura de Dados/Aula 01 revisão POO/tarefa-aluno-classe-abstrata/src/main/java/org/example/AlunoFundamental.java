package org.example;

public class AlunoFundamental extends Aluno {
    private Double nota1;
    private Double nota2;
    private Double nota3;
    private Double nota4;

    public AlunoFundamental(Integer ra, String nome, Double nota1, Double nota2, Double nota3, Double nota4) {
        super(ra, nome);
        this.nota1 = nota1;
        this.nota2 = nota2;
        this.nota3 = nota3;
        this.nota4 = nota4;
    }

    @Override
    public Double calcMedia() {
        return (nota1 + nota2 + nota3 + nota4) / 4;
    }

    @Override
    public String toString() {
        return "AlunoFundamental{" +
                "ra=" + getRa() +
                ", nome='" + getNome() + '\'' +
                ", media='" + calcMedia() + '\'' +
                '}';
    }
}
