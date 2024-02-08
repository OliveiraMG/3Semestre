package school.sptech.avaliacaoweb3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import school.sptech.avaliacaoweb3.entity.Estagiario;
import school.sptech.avaliacaoweb3.repository.EstagiarioRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EstagiarioService {

    @Autowired
    private EstagiarioRepository estagiarioRepository;

    public Estagiario save(Estagiario estagiario) {
        if (estagiarioRepository.findByCpf(estagiario.getCpf()) != null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Já existe um estagiário com esse CPF");
        } else {
            return estagiarioRepository.save(estagiario);
        }
    }

    public List<Estagiario> findAll() {
        return estagiarioRepository.findAll();
    }

    public List<Estagiario> findAllByEmpresaParceiraNome(String nome) {
        return estagiarioRepository.findAllByEmpresaParceiraNome(nome);
    }

    public Estagiario findById(Integer id) {
        Optional<Estagiario> estagiarioEncontrado = estagiarioRepository.findById(id);
        if (estagiarioEncontrado.isPresent()) {
            return estagiarioEncontrado.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Estagiário não encontrado");
        }
    }

    public Estagiario vincularEmpresaParceira(Integer idEstagiario, Integer idEmpresaParceira) {
        Optional<Estagiario> estagiarioEncontrado = estagiarioRepository.findById(idEstagiario);
        if (estagiarioEncontrado.isPresent()) {
            Estagiario estagiario = estagiarioEncontrado.get();
//            estagiario.setEmpresaParceira(estagiarioRepository.findByEmpresaParceiraId(idEmpresaParceira));
            return estagiarioRepository.save(estagiario);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Estagiário não encontrado");
        }
    }
}
