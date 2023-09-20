package com.example.aula05jpa.controller;

import com.example.aula05jpa.entity.RegistroFinanceiro;
import com.example.aula05jpa.repository.RegistroFinanceiroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/registros")
public class RegistroFinanceiroController {

    @Autowired
    private RegistroFinanceiroRepository repository;

    public List<RegistroFinanceiro> listaRegistros = new ArrayList<>();

    @PostMapping
    public ResponseEntity<RegistroFinanceiro> postRegistro(@RequestBody RegistroFinanceiro registroFinanceiro){
        if (registroFinanceiro.getData() == null ||
                registroFinanceiro.getDescricao() == null ||
                registroFinanceiro.getValor() == 0.0){
            return ResponseEntity.status(400).build();
        }else{
            RegistroFinanceiro registroSalvo = this.repository.save(registroFinanceiro);
//            listaRegistros.add(registroFinanceiro);
            return ResponseEntity.status(201).body(registroSalvo);
        }

    }

    @GetMapping
    public ResponseEntity<List<RegistroFinanceiro>> getListaRegistros(){

        List <RegistroFinanceiro> listaRegistros = this.repository.findAll();
        return ResponseEntity.status(200).body(listaRegistros);

//        if (listaRegistros.isEmpty()){
//            return ResponseEntity.status(204).build();
//        }else{
//            return ResponseEntity.status(200).body(listaRegistros);
//        }
    }

    @GetMapping("/{index}")
    public ResponseEntity<RegistroFinanceiro> getRegistroPorIndex(@PathVariable int index){
        Optional<RegistroFinanceiro> registroOpt = this.repository.findById(index);

        if (registroOpt.isPresent()){
            RegistroFinanceiro registroFinanceiro = registroOpt.get();
            return ResponseEntity.status(200).body(registroFinanceiro);
        }
        return ResponseEntity.status(404).build();

//        if(verificaIndex(index)){
//            return ResponseEntity.status(200).body(listaRegistros.get(index));
//        }else{
//            return ResponseEntity.status(404).build();
//        }
    }

    @PutMapping("/{index}")
    public ResponseEntity<RegistroFinanceiro> putRegistroPorIndex(@PathVariable int index, @RequestBody RegistroFinanceiro registroFinanceiro){

        if (this.repository.existsById(index)) {
            registroFinanceiro.setId(index);
            RegistroFinanceiro registroFinanceiroSalvo = this.repository.save(registroFinanceiro);
            return ResponseEntity.status(200).body(registroFinanceiroSalvo);
        }
        return ResponseEntity.status(404).build();

//        if(verificaIndex(index)){
//            listaRegistros.set(index, registroFinanceiro);
//            return ResponseEntity.status(200).body(listaRegistros.get(index));
//
//        }else{
//            return ResponseEntity.status(404).build();
//        }

    }

    @DeleteMapping("/{index}")
    public ResponseEntity<Void> delPorIndex(@PathVariable int index){
        if(this.repository.existsById(index)){
            this.repository.deleteById(index);
            return ResponseEntity.status(200).build();
        }else{
            return ResponseEntity.status(404).build();
        }
    }

    @GetMapping("/ganhos")
    public ResponseEntity<List<RegistroFinanceiro>> getResgistrosPositivos(){
        List <RegistroFinanceiro> listaGanhos;
        listaGanhos = listaRegistros.stream().filter(p -> p.getValor() > 0).toList();
        if (listaGanhos.isEmpty()){
            return ResponseEntity.status(204).build();
        }else{
            return ResponseEntity.status(200).body(listaGanhos);
        }
    }

    @GetMapping("/despesas")
    public ResponseEntity<List<RegistroFinanceiro>> getResgistrosNegativos(){
        List <RegistroFinanceiro> listaDespesas;
        listaDespesas = listaRegistros.stream().filter(p -> p.getValor() < 0).toList();
        if (listaDespesas.isEmpty()){
            return ResponseEntity.status(204).build();
        }else{
            return ResponseEntity.status(200).body(listaDespesas);
        }
    }


    public boolean verificaIndex(int index){
        return index >= 0 && index < listaRegistros.size();
    }

}
