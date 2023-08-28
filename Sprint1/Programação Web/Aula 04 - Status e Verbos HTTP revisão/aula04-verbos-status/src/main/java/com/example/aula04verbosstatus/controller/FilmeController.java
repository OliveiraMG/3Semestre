package com.example.aula04verbosstatus.controller;

import com.example.aula04verbosstatus.entity.Filme;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/filmes")
public class FilmeController {

    List<Filme> listaFilmes = new ArrayList<>();


    // GET - http://localhost:8080/filmes
    @GetMapping
    public ResponseEntity<List<Filme>> listar() {

        if(listaFilmes.isEmpty()) {
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(200).body(listaFilmes);
    }

    // GET - http://localhost:8080/filmes/0
    @GetMapping("/{indice}")
    public ResponseEntity<Filme> consultarPorIndice(@PathVariable int indice) {
        if (!isIndiceValido(indice)) {
            return ResponseEntity.status(404).build();
        } else {
            return ResponseEntity.status(200).body(listaFilmes.get(indice));
        }
    }

    // POST - http://localhost:8080/filmes
    @PostMapping
    public ResponseEntity<Filme> cadastrar(@RequestBody Filme filmeNovo) {
        listaFilmes.add(filmeNovo);
        return ResponseEntity.status(201).body(filmeNovo);
    }

    // PUT - http://localhost:8080/filmes/0
    @PutMapping("/{indice}")
    public ResponseEntity<Filme> editarFilmePorIndice(@PathVariable int indice, @RequestBody Filme filmeEditado) {
        if (!isIndiceValido(indice)) {
            return ResponseEntity.status(404).build();
        } else {
            listaFilmes.set(indice, filmeEditado);
            return ResponseEntity.status(200).body(filmeEditado);
        }
    }

    // DELETE - http://localhost:8080/filmes/0 - 200 0u 204 (Preferível)
    @DeleteMapping("/{indice}")
    public ResponseEntity<Void> deletarFilmePorIndice(@PathVariable int indice) {
        if (!isIndiceValido(indice)) {
            return ResponseEntity.status(404).build();
        } else {
            listaFilmes.remove(indice);
            return ResponseEntity.status(204).build();
        }
    }

    private boolean isIndiceValido(int indice) {
        return indice >= 0 && indice < listaFilmes.size();
    }
}
