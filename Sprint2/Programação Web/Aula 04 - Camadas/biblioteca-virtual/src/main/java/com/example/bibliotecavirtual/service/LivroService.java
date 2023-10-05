package com.example.bibliotecavirtual.service;

import com.example.bibliotecavirtual.entity.Livro;
import com.example.bibliotecavirtual.repository.LivroRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivroService {

    private final LivroRepository livroRepository;

    public LivroService(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }

    public Livro salvar(Livro novoLivro) {
        Livro livroSalvo = this.livroRepository.save(novoLivro);
        return livroSalvo;
    }

    public List<Livro> listar() {
        List<Livro> livros = this.livroRepository.findAll();
        return livros;
    }

    public Livro buscarPorId(Long id) {
        Livro livro = this.livroRepository.findById(id).orElseThrow(() -> new RuntimeException("Livro não encontrado"));
        return livro;
    }

    public Void deletar(Long id) {
        this.livroRepository.deletarLivroPeloId(id);
        return null;
    }

}
