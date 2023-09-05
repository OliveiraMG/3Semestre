package com.example.aula05exercicios.exercicio01.controller;

import com.example.aula05exercicios.exercicio01.entity.Time;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/times")
public class TimeController {

    public List<Time> listaTimes = new ArrayList<>();

    // POST - http://localhost:8080/times
    @PostMapping
    public ResponseEntity<Time> cadastrarTime(@RequestBody Time timeNovo) {
        if (timeNovo.getNome().length() <= 3 || timeNovo.getNome().isBlank()) {
            return ResponseEntity.status(400).build();
        } else if (timeNovo.getTreinador().length() <= 2 || timeNovo.getTreinador().isBlank()) {
            return ResponseEntity.status(400).build();
        } else if (timeNovo.getVitorias() < 0) {
            return ResponseEntity.status(400).build();
        } else if (timeNovo.getDerrotas() < 0) {
            return ResponseEntity.status(400).build();
        } else if (timeNovo.getEmpates() < 0) {
            return ResponseEntity.status(400).build();
        } else {
            listaTimes.add(timeNovo);
            return ResponseEntity.status(201).body(timeNovo);
        }
    }

    // GET - http://localhost:8080/times
    @GetMapping
    public ResponseEntity<List<Time>> listarTimes() {
        if (listaTimes.isEmpty()) {
            return ResponseEntity.status(204).build();
        } else {
            return ResponseEntity.status(200).body(listaTimes);
        }
    }

    // GET - http://localhost:8080/times/{indice}
    @GetMapping("/{indice}")
    public ResponseEntity<Time> exibirTimePorIndice(@PathVariable int indice) {
        if (isIndiceValido(indice)) {
            return ResponseEntity.status(200).body(listaTimes.get(indice));
        } else {
            return ResponseEntity.status(404).build();
        }
    }

    // POST - http://localhost:8080/times/{indice}/registrar-vitoria
    @PostMapping("/{indice}/registrar-vitoria")
    public ResponseEntity<Time> registrarVitoria(@PathVariable int indice) {
        if (isIndiceValido(indice)) {
            Time time = listaTimes.get(indice);
            time.setVitorias(time.getVitorias() + 1);
            return ResponseEntity.status(200).body(time);
        } else {
            return ResponseEntity.status(404).build();
        }
    }

    // POST - http://localhost:8080/times/{indice}/registrar-derrota
    @PostMapping("/{indice}/registrar-derrota")
    public ResponseEntity<Time> registrarDerrota(@PathVariable int indice) {
        if (isIndiceValido(indice)) {
            Time time = listaTimes.get(indice);
            time.setDerrotas(time.getDerrotas() + 1);
            return ResponseEntity.status(200).body(time);
        } else {
            return ResponseEntity.status(404).build();
        }
    }

    // POST - http://localhost:8080/times/{indice}/registrar-empate
    @PostMapping("/{indice}/registrar-empate")
    public ResponseEntity<Time> registrarEmpate(@PathVariable int indice) {
        if (isIndiceValido(indice)) {
            Time time = listaTimes.get(indice);
            time.setEmpates(time.getEmpates() + 1);
            return ResponseEntity.status(200).body(time);
        } else {
            return ResponseEntity.status(404).build();
        }
    }

    public boolean isIndiceValido(int indice) {
        return indice >= 0 && indice < listaTimes.size();
    }
}
