package com.example.filmes.controller;

import com.example.filmes.entity.Filme;
import com.example.filmes.repository.FilmeRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/filmes")
public class FilmeController {

    @Autowired
    private FilmeRepository repository;

    @PostMapping
    public ResponseEntity<Filme> postFilme(@Valid @RequestBody Filme filmeNovo) {
        if (filmeNovo.getNome().isBlank()) {
            return ResponseEntity.status(400).build();
        } else {
            Filme filmeSalvo = this.repository.save(filmeNovo);
            return ResponseEntity.created(null).body(filmeSalvo);
        }
    }

    @GetMapping
    public ResponseEntity<List<Filme>> getFilmes() {
        List<Filme> listaFilmes = this.repository.findAll();
        if (listaFilmes.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(listaFilmes);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Filme> getFilmePorId(@PathVariable int id) {
        return ResponseEntity.of(this.repository.findById(id));

//        Optional<Filme> filmeOpt = this.repository.findById(id);

//        if (filmeOpt.isPresent()) {
//            Filme filme = filmeOpt.get();
//            return ResponseEntity.status(200).body(filme);
//        }
//        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Filme> editarFilme(@PathVariable int id,@Valid @RequestBody Filme filmeEditado) {
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

    // NOVOS ENDPOINTS COM MÉTODOS ABSTRATOS DO REPOSITÓRIO
    @GetMapping("/nome")
    public ResponseEntity<List<Filme>> buscarPorNome(@RequestParam String nome) {
        List<Filme> listaFilmes = this.repository.findByNomeContainsIgnoreCase(nome);
        if (listaFilmes.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(listaFilmes);
        }
    }

    @GetMapping("/diretor")
    public ResponseEntity<List<Filme>> buscarPorDiretor(@RequestParam String diretor) {
        List<Filme> listaFilmes = this.repository.findByDiretorContainsIgnoreCase(diretor);
        if (listaFilmes.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(listaFilmes);
        }
    }

    @GetMapping("/periodo")
    public ResponseEntity<List<Filme>> buscarPorDataLancamento(@RequestParam LocalDate data) {
        List<Filme> listaFilmes = this.repository.findByDataLancamentoLessThanEqual(data);
        if (listaFilmes.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(listaFilmes);
        }
    }

    @GetMapping("/indicados")
    public ResponseEntity<List<Filme>> buscarPorIndicacaoOscar() {
        List<Filme> listaFilmes = this.repository.findByIndicacaoOscarTrue();
        if (listaFilmes.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(listaFilmes);
        }
    }

    @GetMapping("/nao-indicados/quantidade")
    public ResponseEntity<Integer> buscarQuantidadeNaoIndicados() {
        int quantidade = this.repository.countByIndicacaoOscarFalse();
        if (quantidade == 0) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(quantidade);
        }
    }

    @GetMapping("/custo-producao/maior")
    public ResponseEntity<Filme> buscarMaiorCusto() {
        Optional<Filme> filmeOpt = this.repository.findFirstByOrderByCustoProducaoDesc();
        if (filmeOpt.isPresent()) {
            Filme filme = filmeOpt.get();
            return ResponseEntity.ok(filme);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/mais-caros")
    public ResponseEntity<List<Filme>> buscar3MaisCaros() {
        List<Filme> listaFilmes = this.repository.findFirst3ByOrderByCustoProducaoDesc();
        if (listaFilmes.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(listaFilmes);
        }
    }


}
