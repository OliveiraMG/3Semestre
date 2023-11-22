package school.sptech.atividade1.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import school.sptech.atividade1.controller.dto.UsuarioCreateRequestDto;
import school.sptech.atividade1.controller.dto.UsuarioResponseDto;
import school.sptech.atividade1.controller.dto.UsuarioSimpleResponse;
import school.sptech.atividade1.controller.dto.mapper.UsuarioMapper;
import school.sptech.atividade1.entity.Usuario;
import school.sptech.atividade1.entity.exception.EntidadeNaoEncontradaException;
import school.sptech.atividade1.service.UsuarioService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDto>> listar() {
        List<Usuario> usuarios = usuarioService.listar();

        if (usuarios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        List<UsuarioResponseDto> dtos = usuarios.stream()
                .map(UsuarioMapper::toUsuarioReponseDto)
                .toList();

        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDto> buscarPorId(@PathVariable int id) {
        // FIXME: Implementar lógica para converter a entidade em DTO
        Usuario usuario = usuarioService.buscarPorId(id);

        return ResponseEntity.ok(UsuarioMapper.toUsuarioReponseDto(usuario));
    }

    @GetMapping("/resumo")
    public ResponseEntity<List<UsuarioSimpleResponse>> listarResumo() {
        // FIXME: Implementar lógica para converter a entidade em DTO
        List<Usuario> usuarios = usuarioService.listar();

        if (usuarios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        List<UsuarioSimpleResponse> dtos = usuarios.stream()
                .map(UsuarioMapper::toUsuarioSimpleResponse)
                .toList();

        return ResponseEntity.ok(dtos);
    }

    @PostMapping
    public ResponseEntity<UsuarioResponseDto> salvar(@RequestBody @Valid UsuarioCreateRequestDto usuarioDto) {
        // FIXME: Implementar lógica para converter a entidade em DTO
        Usuario usuario = UsuarioMapper.toEntity(usuarioDto);

        Usuario usuarioSalvo = usuarioService.salvar(usuario);

        return ResponseEntity.created(null).body(UsuarioMapper.toUsuarioReponseDto(usuarioSalvo));
    }
}
