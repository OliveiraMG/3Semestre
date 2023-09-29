package com.example.filmes.repository;

import com.example.filmes.entity.Filme;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface FilmeRepository extends JpaRepository<Filme, Integer>  {

    // Like %s%, ignorando case
    List<Filme> findByNomeContainsIgnoreCase(String nome);

    // Like %s, ignorando case
    List<Filme> findByDiretorContainsIgnoreCase(String nome);

    // Buscando filmes que foram lançados antes ou até a data informada
    List<Filme> findByDataLancamentoLessThanEqual(LocalDate dataLancamento);

    // Buscando filmes que tem indicação ao Oscar
    List<Filme> findByIndicacaoOscarTrue();

    // Buscando quantidade de filmes que não tem indicação ao oscar
    int countByIndicacaoOscarFalse();

    // Buscando filme com maior custo de produção
    Optional<Filme> findFirstByOrderByCustoProducaoDesc();

    // Buscando 3 filmes com maior custo de produção
    List<Filme> findFirst3ByOrderByCustoProducaoDesc();

}
