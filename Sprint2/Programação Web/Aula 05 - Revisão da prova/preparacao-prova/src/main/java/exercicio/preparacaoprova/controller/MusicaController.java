package exercicio.preparacaoprova.controller;

import exercicio.preparacaoprova.entity.Musica;
import exercicio.preparacaoprova.repository.MusicaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/musicas")
public class MusicaController {

    private MusicaRepository musicaRepository;

    public MusicaController(MusicaRepository musicaRepository) {
        this.musicaRepository = musicaRepository;
    }

    //Enpoint adicional
    @GetMapping
    public ResponseEntity<List<Musica>> listagemGeral() {
        if (musicaRepository.findAll().isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(musicaRepository.findAll());
    }

    //Enpoint adicional
    @GetMapping("/{id}")
    public ResponseEntity<Musica> buscarPorId(@PathVariable UUID id) {
        if (musicaRepository.findById(id).isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(musicaRepository.findById(id).get());
    }

    @GetMapping("/artista")
    public ResponseEntity<List<Musica>> buscarPorArtista(@RequestParam String nome) {
        List<Musica> musicas = this.musicaRepository.findByArtista(nome);
        if (musicas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(musicas);
    }

    @GetMapping("/nome")
    public ResponseEntity<List<Musica>> buscarPorTitulo(@RequestParam String titulo) {
        List<Musica> musicas = this.musicaRepository.findAllMusicaByNomeContainsIgnoreCase(titulo);
        if (musicas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(musicas);
    }

    @GetMapping("/genero/contagem")
    public ResponseEntity<Integer> contarPorGenero(@RequestParam String nome) {
        return ResponseEntity.ok(this.musicaRepository.contarPorGenero(nome));
    }

    @GetMapping("/nao-lancadas")
    public ResponseEntity<List<Musica>> buscarNaoLancadas() {
        List<Musica> musicasNaoLancadas = this.musicaRepository.findAllMusicaByDataLancamentoGreaterThanEqual(LocalDate.now());

        if (musicasNaoLancadas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(musicasNaoLancadas);
    }

    @GetMapping("/lancadas")
    public ResponseEntity<List<Musica>> buscarLancadas() {
        List<Musica> musicasLancadas = this.musicaRepository.findAllMusicaByDataLancamentoLessThan(LocalDate.now());

        if (musicasLancadas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(musicasLancadas);
    }

    @GetMapping("/mais-acessos")
    public ResponseEntity<Musica> buscarMaisPopular() {
        Musica musicaMaisPopular = this.musicaRepository.findFirstByOrderByAcessosDesc();
        if (musicaMaisPopular == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(musicaMaisPopular);
    }

    @GetMapping("/top3-notas")
    public ResponseEntity<List<Musica>> buscarTop3MaioresNotas() {
        List<Musica> top3MaioresNotas = this.musicaRepository.findTop3ByOrderByNotaDesc();
        if (top3MaioresNotas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(top3MaioresNotas);
    }

    @GetMapping("/genero/acessos/contagem")
    public ResponseEntity<Integer> buscarQuantidadeAcessosPorGenero(@RequestParam String nome) {
        Integer quantidadeAcessos = this.musicaRepository.buscarTotalAcessosPorGenero(nome);
        if (quantidadeAcessos == null) {
            return ResponseEntity.ok(0);
        }

        return ResponseEntity.ok(quantidadeAcessos);
    }

    @GetMapping("/menor-nota")
    public ResponseEntity<Musica> buscarMenorNota() {
        Musica musicaMenorNota = this.musicaRepository.findFirstByOrderByNotaAsc();
        if (musicaMenorNota == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(musicaMenorNota);
    }
}
