package com.example.filmesIMyDB.controller;

import com.example.filmesIMyDB.entity.Filme;
import com.example.filmesIMyDB.repository.FilmeRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/filmes")
public class FilmeController {

    @Autowired
    private FilmeRepository repository;

    @PostMapping
    public ResponseEntity<Filme> postFilme(@RequestBody @Valid Filme filmeNovo) {
        if (filmeNovo.getTitulo().isBlank()) {
            return ResponseEntity.status(400).build();
        } else {
            Filme filmeSalvo = this.repository.save(filmeNovo);
            return ResponseEntity.status(201).body(filmeSalvo);
        }
    }

    @GetMapping
    public ResponseEntity<List<Filme>> getFilmes() {
        List<Filme> listaFilmes = this.repository.findAll();
        return ResponseEntity.status(200).body(listaFilmes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Filme> getFilmePorId(@PathVariable int id) {
        Optional<Filme> filmeOpt = this.repository.findById(id);

        if (filmeOpt.isPresent()) {
            Filme filme = filmeOpt.get();
            return ResponseEntity.status(200).body(filme);
        }
        return ResponseEntity.status(404).build();
    }

    @PutMapping
    public ResponseEntity<Filme> editarFilme(@PathVariable int id, @RequestBody Filme filmeEditado) {
        if(this.repository.existsById(id)) {
            filmeEditado.setId(id);
            Filme filmeEditadoSalvo = this.repository.save(filmeEditado);
            return ResponseEntity.status(200).body(filmeEditadoSalvo);
        }
        return ResponseEntity.status(404).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Filme> deletarFilme(@PathVariable int id) {
        if(this.repository.existsById(id)) {
            this.repository.deleteById(id);
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(404).build();
    }

}
