package exercicio.preparacaoprova.repository;

import exercicio.preparacaoprova.entity.Musica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface MusicaRepository extends JpaRepository<Musica, UUID> {

    //1. Buscar musicas por nome do artista (valor exato)
    List<Musica> findByArtista(String artista);

    //2. Buscar musicas por parte do nome (valor parcial) ignore case
    List<Musica> findAllMusicaByNomeContainsIgnoreCase(String nome);

    //3. Contar todas as musicas de um genero (JQPL - valor exato)
    @Query("SELECT COUNT(m) FROM Musica m WHERE m.genero = :genero")
    Integer contarPorGenero(String genero);

    //4. Buscar musicas não lançadas
    List<Musica> findAllMusicaByDataLancamentoGreaterThanEqual(LocalDate data);

    //5. Buscar musicas já lancadas
    List<Musica> findAllMusicaByDataLancamentoLessThan(LocalDate data);

    //6. Buscar musica com mais acessos (quantidade de acessos)
    Musica findFirstByOrderByAcessosDesc();

    //7. Buscar top 3 musicas com maior nota
    List<Musica> findTop3ByOrderByNotaDesc();

    //8. Buscar total de acessos a partir de um gênero (JPQL - valor exato - SUMARIZACAO)
    @Query("SELECT SUM(m.acessos) FROM Musica m WHERE m.genero = :genero")
    Integer buscarTotalAcessosPorGenero(String genero);

    //9. Buscar musica com menor nota (JQPL)
    Musica findFirstByOrderByNotaAsc();
}
