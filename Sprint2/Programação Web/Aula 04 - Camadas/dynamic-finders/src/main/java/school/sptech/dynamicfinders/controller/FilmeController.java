package school.sptech.dynamicfinders.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import school.sptech.dynamicfinders.entity.Filme;
import school.sptech.dynamicfinders.repository.FilmeRepository;
import school.sptech.dynamicfinders.service.FilmeService;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/filmes")
public class FilmeController {

  private FilmeService filmeService;

  public FilmeController(FilmeService filmeService) {
    this.filmeService = filmeService;
  }

//  @Autowired // Injetando depedência na inicialização da controller (poderia ser feito via construtor)
//  private FilmeRepository filmeRepository;

  @PostMapping
  public ResponseEntity<Filme> criar(@Valid @RequestBody Filme novoFilme) {
    Filme filmeSalvo = this.filmeService.salvar(novoFilme);

    URI uri = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(filmeSalvo.getId())
            .toUri();

    // normalmente o created seria /filmes/id -> id do filme criado para consulta posterior
    return ResponseEntity.created(uri).body(filmeSalvo);
  }

  @GetMapping
  public ResponseEntity<List<Filme>> listar() {
    // Buscando todos os filmes
    List<Filme> filmes = this.filmeService.listar();

    if (filmes.isEmpty()) {
//      return ResponseEntity.status(204).build();
      return ResponseEntity.noContent().build();
    }

//    return ResponseEntity.status(200).body(filmes);
    return ResponseEntity.ok(filmes);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Filme> buscarPorId(@PathVariable Integer id) {
    Filme filmeEncontrado = this.filmeService.buscarPorId(id);
    return ResponseEntity.ok(filmeEncontrado);

//    if (filmeOpt.isPresent()) {
//      return ResponseEntity.ok(filmeOpt.get());
//    }
//
//    return ResponseEntity.notFound().build();
  }

//  @PutMapping("/{id}")
//  public ResponseEntity<Filme> atualizar(
//          @PathVariable Integer id,
//          @Valid @RequestBody Filme filmeAtualizado) { // @Valid antes do @RequestBody para validar o objeto a seguir
//
//    // Verificação de contagem por id na base (se existe alguém por esse id)
//    if (this.filmeRepository.existsById(id)) {
//      // setando o id da busca para atualização
//      filmeAtualizado.setId(id);
//
//      // Ao utilizar .save, se o objeto passado estiver com um id que consta na base, ele atualizará o registro
//      Filme filmeSalvo = this.filmeRepository.save(filmeAtualizado);
//      return ResponseEntity.ok(filmeSalvo);
//    }
//
//    return ResponseEntity.notFound().build();
//  }
//
//  @DeleteMapping("/{id}")
//  public ResponseEntity<Void> deletar(@PathVariable Integer id) {
//    if (this.filmeRepository.existsById(id)) {
//      this.filmeRepository.deleteById(id);
//      return ResponseEntity.noContent().build();
//    }
//    return ResponseEntity.notFound().build();
//  }
//
//  @GetMapping("/titulo")
//  public ResponseEntity<List<Filme>> buscarPorTitulo(@RequestParam String nome) { // @RequestParam serve para passar paramêtros de busca
//
//    // Utilizando um dynamic finder criado na repository
//    List<Filme> filmes = this.filmeRepository.findByNomeContainsIgnoreCase(nome);
//
//    if (filmes.isEmpty()) {
//      return ResponseEntity.noContent().build();
//    }
//    return ResponseEntity.ok(filmes);
//  }
//
//  @GetMapping("/diretor")
//  public ResponseEntity<List<Filme>> buscarPorDiretor(@RequestParam String nome) {
//    List<Filme> filmes = this.filmeRepository.findByDiretorContainsIgnoreCase(nome);
//
//    if (filmes.isEmpty()) {
//      return ResponseEntity.noContent().build();
//    }
//    return ResponseEntity.ok(filmes);
//  }
//
//  @GetMapping("/periodo")
//  public ResponseEntity<List<Filme>> buscarPorPeriodo(@RequestParam LocalDate data) {
//    List<Filme> filmes = this.filmeRepository.findByDataLancamentoLessThanEqual(data);
//
//    if (filmes.isEmpty()) {
//      return ResponseEntity.noContent().build();
//    }
//    return ResponseEntity.ok(filmes);
//  }
//
//  @GetMapping("/indicados")
//  public ResponseEntity<List<Filme>> buscarPorIndicados() {
//    List<Filme> filmes = this.filmeRepository.findByIndicacaoOscarTrue();
//
//    if (filmes.isEmpty()) {
//      return ResponseEntity.noContent().build();
//    }
//    return ResponseEntity.ok(filmes);
//  }
//
//  @GetMapping("/nao-indicados/quantidade")
//  public ResponseEntity<Long> buscarPorNaoIndicados() {
//    Long qtdNaoIndicados = this.filmeRepository.countByIndicacaoOscarFalse();
//    return ResponseEntity.ok(qtdNaoIndicados);
//  }
//
//  @GetMapping("/custo-producao")
//  public ResponseEntity<Filme> maiorCusto() {
//
//    Optional<Filme> filmeOpt =
//            this.filmeRepository.findFirstByOrderByCustoProducaoDesc();
//
//    return ResponseEntity.of(filmeOpt);
//
////    if (filmeOpt.isPresent()) {
////      return ResponseEntity.ok(filmeOpt.get());
////    }
////    return ResponseEntity.notFound().build();
//  }
//
//  @GetMapping("/custo-producao/mais-caros")
//  public ResponseEntity<List<Filme>> buscarOs3MaisCaros() {
//
//    List<Filme> maisCaros = this.filmeRepository.findTop3ByOrderByCustoProducaoDesc();
//
//    if (maisCaros.isEmpty()) {
//      return ResponseEntity.noContent().build();
//    }
//
//    return ResponseEntity.ok(maisCaros);
//  }
//
//  // http://localhost:8080/filmes/diretor/indicacao
//  @GetMapping("/diretor/indicacao")
//  public ResponseEntity<List<Filme>> buscarPorDiretorIndicacao(@RequestParam String nome, @RequestParam(required = false) boolean indicado) {
//    List<Filme> filmesEncontrados = this.filmeRepository.buscarPorDiretorIndicadosOscar(nome, indicado);
//
//    if (filmesEncontrados.isEmpty()) {
//      return ResponseEntity.noContent().build();
//    }
//
//    return ResponseEntity.ok(filmesEncontrados);
//
//  }
//
//    // http://localhost:8080/filmes/diretor/indicacao/contagem
//    @GetMapping("/diretor/indicacao/contagem")
//    public ResponseEntity<Integer> buscarPorDiretorIndicacaoContagem(@RequestParam String nome, @RequestParam boolean indicado) {
//      Integer contagem = this.filmeRepository.contagemPorDiretorIndicadosOscar(nome, indicado);
//
//      if (contagem == 0) {
//        return ResponseEntity.noContent().build();
//      }
//
//      return ResponseEntity.ok(contagem);
//
//    }
//
//    // http://localhost:8080/filmes/1/nome
//    @PatchMapping("/{id}/nome")
//    public ResponseEntity<Void> renomearFilme(@PathVariable Integer id, @RequestParam String nome) {
//
//      if (this.filmeRepository.existsById(id)) {
//        Integer linhasAfetadas = this.filmeRepository.renomearFilme(nome, id);
//        System.out.println(linhasAfetadas);
//        return ResponseEntity.noContent().build();
//      }
//      return ResponseEntity.notFound().build();
//    }
//
//
}