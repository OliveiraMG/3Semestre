package com.example.filmesIMyDB.repository;

import com.example.filmesIMyDB.entity.Filme;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmeRepository extends JpaRepository<Filme, Integer> {
}
