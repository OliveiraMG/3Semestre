package com.example.validacoes.repository;

import com.example.validacoes.entity.Musica;
import org.springframework.data.jpa.repository.JpaRepository;

// Camada que vai lidar com o BD
public interface MusicaRepository extends JpaRepository<Musica, Integer> {
}
