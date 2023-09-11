package com.example.aula06jpa.controller;

import com.example.aula06jpa.dtos.RegistroFinanceiroDTO;
import com.example.aula06jpa.entity.RegistroFinanceiro;
import com.example.aula06jpa.services.RegistroFinanceiroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/registros")
public class RegistroFinanceiroController {

    @Autowired
    private RegistroFinanceiroService registroFinanceiroService;

    // POST - http://localhost:8080/registros
    @PostMapping
    public ResponseEntity<RegistroFinanceiro> criarRegistroFinanceiro(@RequestBody RegistroFinanceiroDTO registroFinanceiro) {
        RegistroFinanceiro newRegistroFinanceiro = registroFinanceiroService.criarRegistroFinanceiro(registroFinanceiro);
        return new ResponseEntity<>(newRegistroFinanceiro, HttpStatus.CREATED);
    }

    // GET - http://localhost:8080/registros
    @GetMapping
    public ResponseEntity<List<RegistroFinanceiro>> getAllRegistrosFinanceiros() {
        List<RegistroFinanceiro> registros = registroFinanceiroService.getAllRegistrosFinanceiros();
        return new ResponseEntity<>(registros, HttpStatus.OK);
    }

    // GET - http://localhost:8080/registros/1
    @GetMapping("/{id}")
    public ResponseEntity<RegistroFinanceiro> getRegistroFinanceiroById(@PathVariable Long id) throws Exception {
        RegistroFinanceiro registroFinanceiro = registroFinanceiroService.findRegistroFinanceiroById(id);
        return new ResponseEntity<>(registroFinanceiro, HttpStatus.OK);
    }

    // PUT - http://localhost:8080/registros/1
    @PutMapping("/{id}")
    public ResponseEntity<RegistroFinanceiro> updateRegistroFinanceiro(@PathVariable Long id, @RequestBody RegistroFinanceiro registroFinanceiro) throws Exception {
        RegistroFinanceiro newRegistroFinanceiro = registroFinanceiroService.updateRegistroFinanceiro(id, registroFinanceiro);
        return new ResponseEntity<>(newRegistroFinanceiro, HttpStatus.OK);
    }

    // DELETE - http://localhost:8080/registros/1
    @DeleteMapping("/{id}")
    public ResponseEntity<RegistroFinanceiro> deleteRegistroFinanceiro(@PathVariable Long id) throws Exception {
        RegistroFinanceiro registroFinanceiro = registroFinanceiroService.findRegistroFinanceiroById(id);
        registroFinanceiroService.deleteRegistroFinanceiro(registroFinanceiro);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // GET REGISTROS FINANCEIROS POSITIVOS - http://localhost:8080/registros/ganhos
    @GetMapping("/ganhos")
    public ResponseEntity<List<RegistroFinanceiro>> getAllRegistrosFinanceirosPositivos() {
        List<RegistroFinanceiro> registros = registroFinanceiroService.getAllRegistrosFinanceirosPositivos();
        return new ResponseEntity<>(registros, HttpStatus.OK);
    }

    // GET REGISTROS FINANCEIROS NEGATIVOS - http://localhost:8080/registros/despesas
    @GetMapping("/despesas")
    public ResponseEntity<List<RegistroFinanceiro>> getAllRegistrosFinanceirosNegativos() {
        List<RegistroFinanceiro> registros = registroFinanceiroService.getAllRegistrosFinanceirosNegativos();
        return new ResponseEntity<>(registros, HttpStatus.OK);
    }
}
