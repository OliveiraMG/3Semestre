package school.sptech.marketplaceresumido.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import school.sptech.marketplaceresumido.service.produto.ProdutoService;
import school.sptech.marketplaceresumido.controller.dto.ProdutoAtualizacaoDto;
import school.sptech.marketplaceresumido.controller.dto.ProdutoConsultaDto;
import school.sptech.marketplaceresumido.controller.dto.ProdutoCriacaoDto;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public ResponseEntity<List<ProdutoConsultaDto>> listar() {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoConsultaDto> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<ProdutoConsultaDto> criar(@RequestBody @Valid ProdutoCriacaoDto produtoCriacaoDto) {
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoConsultaDto> atualizar(
            @PathVariable Long id,
            @RequestBody @Valid ProdutoAtualizacaoDto produtoCriacaoDto) {

        return ResponseEntity.ok().build();
    }
}
