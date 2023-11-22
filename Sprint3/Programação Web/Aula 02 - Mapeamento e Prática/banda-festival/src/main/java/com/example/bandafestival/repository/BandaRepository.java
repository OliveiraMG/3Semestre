package com.example.bandafestival.repository;

import com.example.bandafestival.entity.Banda;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BandaRepository extends JpaRepository<Banda, Integer> {

    Optional<Banda> findByNomeBanda(String nomeBanda);

    List<Banda> findByNomeBandaContainsIgnoreCase(String nomeBanda);

    List<Banda> findByGeneroIgnoreCase(String genero);

    List<Banda> findTop3ByOrderByDiaDeFormacaoAsc();

}