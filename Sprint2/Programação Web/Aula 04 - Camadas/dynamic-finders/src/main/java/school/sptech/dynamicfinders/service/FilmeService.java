package school.sptech.dynamicfinders.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import school.sptech.dynamicfinders.entity.Filme;
import school.sptech.dynamicfinders.repository.FilmeRepository;

import java.util.List;

/*

 @Component
 @Controller -> html
 @RestController
 @Service
 @Repository
 @Configuration

 */

@Service
public class FilmeService {

    private final FilmeRepository filmeRepository;

    public FilmeService(FilmeRepository filmeRepository) {
        this.filmeRepository = filmeRepository;
    }

    public Filme salvar(Filme filmeNovo) {
        Filme filmeSalvo = this.filmeRepository.save(filmeNovo);
        return filmeSalvo;
    }

    public List<Filme> listar() {
        List<Filme> filmes = this.filmeRepository.findAll();
        return filmes;
    }

    public Filme buscarPorId(Integer id) {
        Filme filme = this.filmeRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Filme não encontrado"));
        return filme;
    }
}