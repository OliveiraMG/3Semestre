package com.example.validacoes.controller;

import com.example.validacoes.entity.Musica;
import com.example.validacoes.repository.MusicaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/musicas")
public class MusicaController {

    @Autowired
    private MusicaRepository repository;

    @PostMapping
    public ResponseEntity<Musica> postMusica(@RequestBody @Valid Musica musicaNova) {
        if (musicaNova.getNome().isBlank()) {
            return ResponseEntity.status(400).build();
        } else {
            Musica musicaSalva = this.repository.save(musicaNova);
            return ResponseEntity.status(201).body(musicaSalva);
        }
    }

    @GetMapping
    public ResponseEntity<List<Musica>> getMusicas() {
        List<Musica> listaMusicas = this.repository.findAll();
        return ResponseEntity.status(200).body(listaMusicas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Musica> getMusicaPorId(@PathVariable int id) {
        Optional<Musica> musicaOpt = this.repository.findById(id);

        if (musicaOpt.isPresent()) {
            Musica musica = musicaOpt.get();
            return ResponseEntity.status(200).body(musica);
        }
        return ResponseEntity.status(404).build();
    }

    @PutMapping
    public ResponseEntity<Musica> editarMusica(@PathVariable int id, @RequestBody Musica musicaEditada) {
        if(this.repository.existsById(id)) {
            musicaEditada.setId(id);
            Musica musicaEditadaSalva = this.repository.save(musicaEditada);
            return ResponseEntity.status(200).body(musicaEditadaSalva);
        }
        return ResponseEntity.status(404).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Musica> deletarMusica(@PathVariable int id) {
        if(this.repository.existsById(id)) {
            this.repository.deleteById(id);
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(404).build();
    }
}
