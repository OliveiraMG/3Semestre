package projeto.aula03;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/musicas")
public class MusicaController {

    private List<Musica> listaMusicas= new ArrayList<>();

    @GetMapping("/listar")
    public List<Musica> listarMusicas() {
        return listaMusicas;
    }

    @GetMapping("/consultar/{indice}")
    public Musica consultarPorIndice(@PathVariable int indice) {
    if (isValido(indice)) {
        return listaMusicas.get(indice);
    } else {
        return null;
    }
    }

    @GetMapping("/cadastrar/{nome}/{artista}")
    public Musica cadastrarMusica(@PathVariable String nome, @PathVariable String artista) {
        Musica musica = new Musica(nome, artista);
        listaMusicas.add(musica);
        return musica;
    }

    public boolean isValido(int indice) {
        return indice >= 0 && indice < listaMusicas.size();
    }

}
