package school.sptech.avaliacaoweb3.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.avaliacaoweb3.entity.EmpresaParceira;
import school.sptech.avaliacaoweb3.service.EmpresaParceiraService;

import java.util.List;

@RestController
@RequestMapping("/empresas")
public class EmpresaParceiraController {

    @Autowired
    private EmpresaParceiraService empresaParceiraService;

    @GetMapping
    public ResponseEntity<List<EmpresaParceira>> findAll() {
        if (empresaParceiraService.findAll().isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(empresaParceiraService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmpresaParceira> findById(@PathVariable int id) {
        return ResponseEntity.ok(empresaParceiraService.findById(id));
    }

    @PostMapping
    public ResponseEntity<EmpresaParceira> save(@RequestBody @Valid EmpresaParceira empresaParceira) {
        return ResponseEntity.ok(empresaParceiraService.save(empresaParceira));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmpresaParceira> update(
            @PathVariable int id,
            @RequestBody @Valid EmpresaParceira empresaParceira
    ) {
        return ResponseEntity.ok(empresaParceiraService.update(id, empresaParceira));
    }
}
