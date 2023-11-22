package school.sptech.atividade1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import school.sptech.atividade1.entity.Usuario;
import school.sptech.atividade1.entity.exception.EntidadeNaoEncontradaException;
import school.sptech.atividade1.repository.UsuarioRepository;

import java.util.List;

//FIXME: Completar a classe
@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public Usuario salvar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public List<Usuario> listar() {
        return usuarioRepository.findAll();
    }

    public Usuario buscarPorId(Integer id) {
        return usuarioRepository.findById(id).orElseThrow(() -> new EntidadeNaoEncontradaException("Usuário não encontrado"));
    }
}
