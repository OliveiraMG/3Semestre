package com.example.bibliotecavirtual.controller;

import com.example.bibliotecavirtual.entity.Livro;
import com.example.bibliotecavirtual.service.LivroService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/livros")
public class LivroController {

    private LivroService livroService;

    public LivroController(LivroService livroService) {
        this.livroService = livroService;
    }

    @PostMapping
    public ResponseEntity<Livro> criar(@Valid @RequestBody Livro novoLivro) {
        Livro livroSalvo = this.livroService.salvar(novoLivro);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(livroSalvo.getId())
                .toUri();

        // normalmente o created seria /livros/id -> id do livro criado para consulta posterior
        return ResponseEntity.created(uri).body(livroSalvo);
    }

    @GetMapping
    public ResponseEntity<List<Livro>> listar() {
        // Buscando todos os livros
        List<Livro> livros = this.livroService.listar();

        if (livros.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(livros);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Livro> buscarPorId(@PathVariable Long id) {
        Livro livroEncontrado = this.livroService.buscarPorId(id);

        return ResponseEntity.ok(livroEncontrado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Livro> atualizar(@PathVariable Long id, @Valid @RequestBody Livro livroAtualizado) {
        Livro livroAtual = this.livroService.buscarPorId(id);
        livroAtual.setTitulo(livroAtualizado.getTitulo());
        livroAtual.setAutor(livroAtualizado.getAutor());
        livroAtual.setDataPublicacao(livroAtualizado.getDataPublicacao());
        livroAtual.setPreco(livroAtualizado.getPreco());
        livroAtual.setDisponibilidadeEstoque(livroAtualizado.isDisponibilidadeEstoque());
        Livro livroSalvo = this.livroService.salvar(livroAtual);

        return ResponseEntity.ok(livroSalvo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        this.livroService.deletar(id);
        return ResponseEntity.noContent().build();
    }

}
