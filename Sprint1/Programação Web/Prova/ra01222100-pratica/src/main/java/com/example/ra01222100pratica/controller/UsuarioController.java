package com.example.ra01222100pratica.controller;

import com.example.ra01222100pratica.entity.Usuario;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    public List<Usuario> listaUsuarios = new ArrayList<>();

    // O e-mail fornecido não deve existir na lista de usuários.

    @PostMapping
    public ResponseEntity<Usuario> cadastrarUsuario(@RequestBody Usuario usuarioNovo) {
        for (Usuario usuario : listaUsuarios) {
            if (usuario.getEmail().equals(usuarioNovo.getEmail())) {
                return ResponseEntity.status(400).build();
            }
        }
        if (usuarioNovo.getEmail().isBlank()|| !(usuarioNovo.getEmail().contains("@")) || usuarioNovo.getEmail().length() < 10) {
            return ResponseEntity.status(400).build();
        }
        else {
            listaUsuarios.add(usuarioNovo);
            return ResponseEntity.status(201).body(usuarioNovo);
        }
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> listarTodosUsuarios() {
        if (listaUsuarios.isEmpty()) {
            return ResponseEntity.status(204).build();
        } else {
            return ResponseEntity.status(200).body(listaUsuarios);
        }
    }

    @GetMapping("/{indice}")
    public ResponseEntity<Usuario> buscarUsuarioPorIndice(@PathVariable int indice) {
        if (!isIndiceValido(indice)) {
            return ResponseEntity.status(404).build();
        } else if(listaUsuarios.isEmpty()) {
            return ResponseEntity.status(204).build();
        } else {
            return ResponseEntity.status(200).body(listaUsuarios.get(indice));
        }
    }

    @PutMapping("/{indice}")
    public ResponseEntity<Usuario> editaUsuaruioPorIndice(@RequestBody Usuario usuarioEditado, @PathVariable int indice) {
        for (Usuario usuario : listaUsuarios) {
            if (usuario.getEmail().equals(usuarioEditado.getEmail())) {
                return ResponseEntity.status(400).build();
            }
        }

        if (!isIndiceValido(indice)) {
            return ResponseEntity.status(404).build();
        } else if(listaUsuarios.isEmpty()) {
            return ResponseEntity.status(204).build();
        } else if (usuarioEditado.getEmail().isBlank()|| !(usuarioEditado.getEmail().contains("@")) || usuarioEditado.getEmail().length() < 10) {
            return ResponseEntity.status(400).build();
        } else {
            listaUsuarios.set(indice, usuarioEditado);
            return ResponseEntity.status(200).body(usuarioEditado);
        }
    }

    @DeleteMapping("/{indice}")
    public ResponseEntity<Usuario> deletarUsuarioPorIndice(@PathVariable int indice) {
        if (!isIndiceValido(indice)) {
            return ResponseEntity.status(404).build();
        } else if(listaUsuarios.isEmpty()) {
            return ResponseEntity.status(204).build();
        } else {
            listaUsuarios.remove(indice);
            return ResponseEntity.status(200).build();
        }
    }

    public boolean isIndiceValido(int indice) {
        return indice >= 0 && indice < listaUsuarios.size();
    }

}
