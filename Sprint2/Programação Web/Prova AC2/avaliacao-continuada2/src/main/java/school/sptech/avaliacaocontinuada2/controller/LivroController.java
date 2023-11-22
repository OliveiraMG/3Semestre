package school.sptech.avaliacaocontinuada2.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import school.sptech.avaliacaocontinuada2.entity.Producao;
import school.sptech.avaliacaocontinuada2.repository.ProducaoRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/producoes")
public class LivroController {

    private ProducaoRepository producaoRepository;

    public LivroController(ProducaoRepository producaoRepository) {
        this.producaoRepository = producaoRepository;
    }

    /*
        Precisamos da sua ajuda aqui!
        Escreva os métodos abaixo:
    */

    //1. Buscar Produções por Nome do Diretor:
    @GetMapping("/diretor")
    public ResponseEntity<List<Producao>> listagemAutor(@RequestParam String nome) {
        List<Producao> producoes = this.producaoRepository.findByDiretor(nome);
        if (producoes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(producoes);
    }

    //2. Buscar Produções por Parte do Título:
    @GetMapping("/titulo")
    public ResponseEntity<List<Producao>> listagemTitulo(@RequestParam String nome) {
        List<Producao> producoes = this.producaoRepository.findAllProducaoByTituloContainsIgnoreCase(nome);
        if (producoes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(producoes);
    }

    //3. Contar Todas as Produções de um Gênero:
    @GetMapping("/genero")
    public ResponseEntity<Integer> listagemEditora(@RequestParam String nome) {
        return ResponseEntity.ok(this.producaoRepository.countProducaoByGenero(nome));
    }

    //4. Buscar Produções com Data de Lançamento Após uma Data Específica:
    @GetMapping("/recentes")
    public ResponseEntity<List<Producao>> listagemRecentes(@RequestParam LocalDate data) {
       List<Producao> producoes = this.producaoRepository.findAllProducaoByDataLancamentoGreaterThan(data);
        if (producoes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(producoes);
    }

    //5. Buscar Produções com Data de Lançamento Antes ou na Data Específica:
    @GetMapping("/antigos")
    public ResponseEntity<List<Producao>> listagemAntigos(@RequestParam LocalDate data) {
        List<Producao> producoes = this.producaoRepository.findAllProducaoByDataLancamentoLessThanEqual(data);
        if (producoes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(producoes);
    }

    //6. Buscar a Produção Mais Votada (maior quantidade de avaliações):
    @GetMapping("/notas/mais-votado")
    public ResponseEntity<Producao> buscarMaisVotado() {
        Producao producaoMaisVotada = this.producaoRepository.findFirstByOrderByQuantidadeAvaliacoesDesc();
        if (producaoMaisVotada == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(producaoMaisVotada);
    }

    //7. Buscar Top 3 Produções com Maior Nota no IMDB:
    @GetMapping("/notas/top3")
    public ResponseEntity<List<Producao>> listagemMaisBaratos() {
        List<Producao> producoes = this.producaoRepository.findTop3ByOrderByNotaImdbDesc();
        if (producoes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(producoes);
    }
}
