package com.example.aula05exercicios.exercicio02.controller;

import com.example.aula05exercicios.exercicio02.entity.Aluno;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    public List<Aluno> listaAlunos = new ArrayList<>();
    // POST - http://localhost:8080/alunos
    @PostMapping
    public ResponseEntity<Aluno> cadastrarAluno(@RequestBody Aluno alunoNovo) {
        if (alunoNovo.getNome().length() <= 3 || alunoNovo.getNome().isBlank()) {
            return ResponseEntity.status(400).build();
        } else if (!alunoNovo.getEmail().contains("@") && !alunoNovo.getEmail().contains(".school") || alunoNovo.getEmail().isBlank()) {
            return ResponseEntity.status(400).build();
        } else if (alunoNovo.getNotaContinuada1() < 0 || alunoNovo.getNotaContinuada1() > 10) {
            return ResponseEntity.status(400).build();
        } else if (alunoNovo.getNotaContinuada2() < 0 || alunoNovo.getNotaContinuada2() > 10) {
            return ResponseEntity.status(400).build();
        } else if (alunoNovo.getNotaContinuada3() < 0 || alunoNovo.getNotaContinuada3() > 10) {
            return ResponseEntity.status(400).build();
        } else if (alunoNovo.getNotaIntegrada() < 0 || alunoNovo.getNotaIntegrada() > 10) {
            return ResponseEntity.status(400).build();
        } else {
            listaAlunos.add(alunoNovo);
            return ResponseEntity.status(201).body(alunoNovo);
        }
    }

    // GET - http://localhost:8080/alunos
    @GetMapping
    public ResponseEntity<List<Aluno>> listarAlunos() {
        if (listaAlunos.isEmpty()) {
            return ResponseEntity.status(204).build();
        } else {
            return ResponseEntity.status(200).body(listaAlunos);
        }
    }

    // GET - http://localhost:8080/alunos/{indice}
    @GetMapping("/{indice}")
    public ResponseEntity<Aluno> buscarAluno(@PathVariable int indice) {
        if (!isIndiceValido(indice)) {
            return ResponseEntity.status(404).build();
        } else {
            return ResponseEntity.status(200).body(listaAlunos.get(indice));
        }
    }

    // PUT - http://localhost:8080/alunos/{indice}
    @PutMapping("/{indice}")
    public ResponseEntity<Aluno> buscarAlunoPorIndice(@RequestBody Aluno novoAluno, @PathVariable int indice) {
        if (!isIndiceValido(indice)) {
            return ResponseEntity.status(404).build();
        } else {
            listaAlunos.set(indice, novoAluno);
            return ResponseEntity.status(200).body(novoAluno);
        }
    }

    // GET - http://localhost:8080/alunos/{email}
    @GetMapping("/{email}")
    public ResponseEntity<Aluno> buscarAlunoPorEmail(@PathVariable String email) {
        for (Aluno aluno : listaAlunos) {
            if (aluno.getEmail().equals(email)) {
                return ResponseEntity.status(200).body(aluno);
            }
        }
        return ResponseEntity.status(404).build();
    }

    // DELETE - http://localhost:8080/alunos/{indice}
    @DeleteMapping("/{indice}")
    public ResponseEntity<Aluno> excluirAluno(@PathVariable int indice) {
        if (!isIndiceValido(indice)) {
            return ResponseEntity.status(404).build();
        } else {
            listaAlunos.remove(indice);
            return ResponseEntity.status(200).build();
        }
    }


    public boolean isIndiceValido(int indice) {
        return indice >= 0 && indice < listaAlunos.size();
    }

}
