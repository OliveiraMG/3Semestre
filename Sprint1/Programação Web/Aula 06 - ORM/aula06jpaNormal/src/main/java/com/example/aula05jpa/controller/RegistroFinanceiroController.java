package com.example.aula05jpa.controller;

import com.example.aula05jpa.entity.RegistroFinanceiro;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/registros")
public class RegistroFinanceiroController {

    public List<RegistroFinanceiro> listaRegistros = new ArrayList<>();

    @PostMapping
    public ResponseEntity<RegistroFinanceiro> postRegistro(@RequestBody RegistroFinanceiro registroFinanceiro){
        if (registroFinanceiro.getData() == null ||
                registroFinanceiro.getDescricao() == null ||
                registroFinanceiro.getValor() == 0.0){

            return ResponseEntity.status(400).build();

        }else{
            listaRegistros.add(registroFinanceiro);
            return ResponseEntity.status(201).body(registroFinanceiro);
        }

    }

    @GetMapping
    public ResponseEntity<List<RegistroFinanceiro>> getListaRegistros(){
        if (listaRegistros.isEmpty()){
            return ResponseEntity.status(204).build();
        }else{
            return ResponseEntity.status(200).body(listaRegistros);
        }
    }

    @GetMapping("/{index}")
    public ResponseEntity<RegistroFinanceiro> getRegistroPorIndex(@PathVariable int index){
        if(verificaIndex(index)){
            return ResponseEntity.status(200).body(listaRegistros.get(index));
        }else{
            return ResponseEntity.status(403).build();
        }
    }

    @PutMapping("/{index}")
    public ResponseEntity<RegistroFinanceiro> putRegistroPorIndex(@PathVariable int index,
                                                                  @RequestBody RegistroFinanceiro registroFinanceiro){
        if(verificaIndex(index)){
            listaRegistros.set(index, registroFinanceiro);
            return ResponseEntity.status(200).body(listaRegistros.get(index));

        }else{
            return ResponseEntity.status(403).build();
        }

    }

    @DeleteMapping("/{index}")
    public ResponseEntity<Void> delPorIndex(@PathVariable int index){
        if(verificaIndex(index)){
            listaRegistros.remove(index);
            return ResponseEntity.status(200).build();
        }else{
            return ResponseEntity.status(403).build();
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
