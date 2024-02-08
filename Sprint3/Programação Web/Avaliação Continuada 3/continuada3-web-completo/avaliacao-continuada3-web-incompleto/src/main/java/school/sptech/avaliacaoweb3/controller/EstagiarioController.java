package school.sptech.avaliacaoweb3.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import school.sptech.avaliacaoweb3.entity.Estagiario;
import school.sptech.avaliacaoweb3.service.EstagiarioService;

import java.util.List;

@RestController
@RequestMapping("/estagiarios")
public class EstagiarioController {

    @Autowired
    private EstagiarioService estagiarioService;

    @GetMapping
    public ResponseEntity<List<Estagiario>> findAll() {
        if (estagiarioService.findAll().isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(estagiarioService.findAll());
    }

    @GetMapping("/empresa")
    public ResponseEntity<List<Estagiario>> findAllByEmpresaNome(@RequestParam String nome) {
        if (estagiarioService.findAllByEmpresaParceiraNome(nome).isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(estagiarioService.findAllByEmpresaParceiraNome(nome));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Estagiario> findById(@PathVariable int id) {
        return ResponseEntity.ok(estagiarioService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Estagiario> save(@RequestBody @Valid Estagiario estagiario) {
        return ResponseEntity.ok(estagiarioService.save(estagiario));
    }

    @PutMapping("/{idEstagiario}/vinculo/{idEmpresaParceira}")
    public ResponseEntity<Estagiario> vincularEmpresaParceira(@PathVariable Integer idEstagiario, @PathVariable Integer idEmpresaParceira) {
        return ResponseEntity.ok(estagiarioService.vincularEmpresaParceira(idEstagiario, idEmpresaParceira));
    }
}
