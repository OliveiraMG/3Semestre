package school.sptech.aula02nivelamento;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/herois")
public class HeroiController {

    private List<Heroi> listaHerois = new ArrayList<>(
            List.of(new Heroi("Homem Sereia", 8001, "Nadar", 1200, true),
                    new Heroi("Batman", 9001, "Descer o k7", 30, true))
    );

    //http:localhost:8080/herois
    @GetMapping
    public List<Heroi> todos() {
        return listaHerois;
    }
    //http://localhost:8080/herois/favorito
    // Desserialização - JSON para objeto
    // Serialização - Transformar para JSON
    @GetMapping("/favorito")
    public Heroi favorito() {
        return new Heroi("Homem Sereia", 8001, "Nadar", 1200, true);
    }

    @GetMapping("/{indice}")
    public Heroi recuperar(@PathVariable int indice) {
        if (!verificarIndiceValido(indice))
            return null;
        else
            return listaHerois.get(indice);
    }

    @GetMapping("/cadastrar/{nome}/{forca}/{habilidade}/{idade}/{vivo}")
    public Heroi cadastrar(@PathVariable String nome, @PathVariable int forca, @PathVariable String habilidade, @PathVariable int idade, @PathVariable boolean vivo) {
        Heroi novoHeroi = new Heroi(nome,forca, habilidade, idade, vivo);
        listaHerois.add(novoHeroi);
        return novoHeroi;
    }

    @GetMapping("/atualizar/{indice}/{nome}/{forca}/{habilidade}/{idade}/{vivo}")
    public Heroi atualizar(@PathVariable int indice,@PathVariable String nome, @PathVariable int forca, @PathVariable String habilidade, @PathVariable int idade, @PathVariable boolean vivo) {
        Heroi heroiAtualizado = new Heroi(nome,forca, habilidade, idade, vivo);
        if (!verificarIndiceValido(indice))
            return null;
        else
            listaHerois.set(indice, heroiAtualizado);
        return heroiAtualizado;
    }

    @GetMapping("/remover/{indice}")
    public String remover(@PathVariable int indice) {
        if (!verificarIndiceValido(indice))
            return "Heroi não encontrado";
        else
            listaHerois.remove(indice);
        return String.format("Heroi de indíce: %d removido!", indice);
    }

    private boolean verificarIndiceValido(int indice) {
        if (listaHerois.size() -1 >= indice && indice >=0) {
            return true;
        }
        return false;
    }
}
