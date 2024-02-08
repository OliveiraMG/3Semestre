package school.sptech.avaliacaoweb3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import school.sptech.avaliacaoweb3.entity.EmpresaParceira;
import school.sptech.avaliacaoweb3.repository.EmpresaParceiraRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EmpresaParceiraService {

    @Autowired
    private EmpresaParceiraRepository empresaParceiraRepository;

    public EmpresaParceira save(EmpresaParceira empresaParceira) {
        if (empresaParceiraRepository.findByCnpj(empresaParceira.getCnpj()) != null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Empresa já cadastrada");
        } else {
            return empresaParceiraRepository.save(empresaParceira);
        }
    }

    public List<EmpresaParceira> findAll() {
        return empresaParceiraRepository.findAll();
    }

    public EmpresaParceira findById(Integer id) {
        Optional<EmpresaParceira> empresaParceiraEncontrada = empresaParceiraRepository.findById(id);
        if (empresaParceiraEncontrada.isPresent()) {
            return empresaParceiraEncontrada.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Empresa não encontrada");
        }
    }

    public EmpresaParceira update(int id, EmpresaParceira empresaParceira) {
        if (empresaParceiraRepository.findById(id).isPresent()) {
            if (empresaParceiraRepository.findByCnpj(empresaParceira.getCnpj()) != null) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Empresa já cadastrada");
            }
            empresaParceira.setId(id);
            return empresaParceiraRepository.save(empresaParceira);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Empresa não encontrada");
        }
    }
}