package com.example.bandafestival.repository;

import com.example.bandafestival.entity.Festival;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FestivalRepository extends JpaRepository<Festival, Integer> {

    Optional<Festival> findByNomeFestival(String nomeFestival);

}
