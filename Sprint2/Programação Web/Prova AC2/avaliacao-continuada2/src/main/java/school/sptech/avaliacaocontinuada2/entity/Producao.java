package school.sptech.avaliacaocontinuada2.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

/*
    Não altere esta classe!!!

    Isso pode impactar sua nota!
    Foque seus esforços no REPOSITORY e CONTROLLER DE PRODUCAO
*/

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Producao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String titulo;

    private String diretor;

    private String genero;

    private String tipo;

    private Double notaImdb;

    private int quantidadeAvaliacoes;

    private LocalDate dataLancamento;
}
