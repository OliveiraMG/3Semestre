package school.sptech.avaliacaocontinuada2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.avaliacaocontinuada2.entity.Producao;

import java.time.LocalDate;
import java.util.List;

    /*
        Precisamos da sua ajuda aqui!
        Escreva os métodos abaixo:
    */

public interface ProducaoRepository extends JpaRepository<Producao, Integer> {

    //1. Buscar producoes por nome do diretor (VALOR EXATO):
    List<Producao> findByDiretor(String diretor);

    // 2. Buscar producoes por parte do titulo (VALOR APROXIMADO ignorando maiusculas e minusculas):
    List<Producao> findAllProducaoByTituloContainsIgnoreCase(String titulo);

    //3. Contar todas as producoes de um genero (VALOR EXATO):
    Integer countProducaoByGenero(String genero);

    //4. Buscar producoes com data de lançamento MAIOR que uma data específica:
    List<Producao> findAllProducaoByDataLancamentoGreaterThan(LocalDate data);

    //5. Buscar producoes com data de lançamento MENOR OU IGUAL que uma data específica:
    List<Producao> findAllProducaoByDataLancamentoLessThanEqual(LocalDate data);

    //6. Buscar producao mais votada (maior quantidade de avaliacoes):
    Producao findFirstByOrderByQuantidadeAvaliacoesDesc();

    //7. Buscar top 3 producoes com maior nota (SOMENTE 3, sendo a primeira a maior):
    List<Producao> findTop3ByOrderByNotaImdbDesc();
}
