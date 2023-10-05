package com.example.bibliotecavirtual.repository;

import com.example.bibliotecavirtual.entity.Livro;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface LivroRepository extends JpaRepository<Livro, Long> {

    @Modifying
    @Transactional
    @Query("UPDATE Livro l SET l.titulo = :nome, l.autor = :autor WHERE l.id = :id")
    Integer renomearLivroAndAutor(String nome, String autor, Long id);

    @Modifying
    @Transactional
    @Query("DELETE FROM Livro l WHERE l.id = :id")
    Void deletarLivroPeloId(Long id);

    @Query("SELECT l FROM Livro l where l.dataPublicacao > :data")
    Integer buscarLivrosNaoPublicados(LocalDate data);


    Livro findById(long id);
    List<Livro> findByDisponibilidadeEstoqueTrue();
    List<Livro> findTop5ByDataPublicacaoLessThan(LocalDate data);
    Livro findFirstByOrderByPrecoDesc();
}
