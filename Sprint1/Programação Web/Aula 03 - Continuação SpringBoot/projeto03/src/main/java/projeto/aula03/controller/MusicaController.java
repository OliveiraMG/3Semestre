package projeto.aula03.controller;

import org.springframework.web.bind.annotation.*;
import projeto.aula03.entity.Musica;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/musicas")
public class MusicaController {

    private List<Musica> listaMusicas= new ArrayList<>();

    // https://localhost:8080/musicas/listar
    @GetMapping // usar verbos da língua portuguesa é má prática
    public List<Musica> listarMusicas() {
        return listaMusicas;
    }

    // https://localhost:8080/musicas/consultar/0
    @GetMapping("/{indice}")
    public Musica consultarPorIndice(@PathVariable int indice) {
    if (isIndiceValido(indice)) {
        return listaMusicas.get(indice);
    } else {
        return null;
    }

    }

    // https://localhost:8080/musicas
    // @GetMapping("/cadastrar/{nome}/{artista/}")
    @PostMapping
    public Musica cadastrarMusica(@RequestBody Musica musica) {
//        Musica musica = new Musica();
        listaMusicas.add(musica);
        return musica;
    }

    // https://localhost:8080/musicas/0
    @PutMapping("/{indice}")
    public Musica editarMusica(@PathVariable int indice, @RequestBody Musica musica) {
        if (isIndiceValido(indice)) {
            listaMusicas.set(indice, musica);
            return musica;
        } else {
            return null;
        }
    }

    // https://localhost:8080/musicas/0
    @DeleteMapping("/{indice}")
    public String deletarMusica(@PathVariable int indice) {
        if (isIndiceValido(indice)) {
            listaMusicas.remove(indice);
            return "Musica removida com sucesso!";
        } else {
            return "Não foi possível remover a música!";
        }
    }

    public boolean isIndiceValido(int indice) {
        return indice >= 0 && indice < listaMusicas.size();
    }

}
