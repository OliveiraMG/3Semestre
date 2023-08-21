package projeto.aula03;

import java.time.LocalDate;

// Tabela musica
// Model
// Entity
// Domain (OBJETO)
public class Musica {

    private String nome;
    private String artista;

    /*
    * LocalDate -> use essa 2023-11-06
    * LocalDateTime -> use essa 2023-11-06T10:15:30
    * DateTime
    * Date
    * Epoch
    * Calendar
    * ...
    * */
    private LocalDate dataCriacao = LocalDate.now();

    // Sobrecarga de construtores
    public Musica() {
    }

    public Musica(String nome, String artista) {
        this.nome = nome;
        this.artista = artista;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public LocalDate getDataCriacaon() {
        return dataCriacaon;
    }

    public void setDataCriacaon(LocalDate dataCriacaon) {
        this.dataCriacaon = dataCriacaon;
    }
}
