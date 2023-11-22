package org.example;

public class Aluno {
    private String ra;
    private String nome;
    private String curso;
    private String nomeDaDisciplina;
    private Double mediaFinal;
    private int quantidadeDeFaltas;

    public Aluno(String ra, String nome, String curso, String nomeDaDisciplina, Double mediaFinal, int quantidadeDeFaltas) {
        this.ra = ra;
        this.nome = nome;
        this.curso = curso;
        this.nomeDaDisciplina = nomeDaDisciplina;
        this.mediaFinal = mediaFinal;
        this.quantidadeDeFaltas = quantidadeDeFaltas;
    }

    @Override
    public String toString() {
        return "Aluno{" +
                "ra='" + ra + '\'' +
                ", nome='" + nome + '\'' +
                ", curso='" + curso + '\'' +
                ", nomeDaDisciplina='" + nomeDaDisciplina + '\'' +
                ", mediaFinal=" + mediaFinal +
                ", quantidadeDeFaltas=" + quantidadeDeFaltas +
                '}';
    }

    public String getRa() {
        return ra;
    }

    public void setRa(String ra) {
        this.ra = ra;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getNomeDaDisciplina() {
        return nomeDaDisciplina;
    }

    public void setNomeDaDisciplina(String nomeDaDisciplina) {
        this.nomeDaDisciplina = nomeDaDisciplina;
    }

    public Double getMediaFinal() {
        return mediaFinal;
    }

    public void setMediaFinal(Double mediaFinal) {
        this.mediaFinal = mediaFinal;
    }

    public int getQuantidadeDeFaltas() {
        return quantidadeDeFaltas;
    }

    public void setQuantidadeDeFaltas(int quantidadeDeFaltas) {
        this.quantidadeDeFaltas = quantidadeDeFaltas;
    }
}
