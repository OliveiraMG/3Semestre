package school.sptech.aula02nivelamento;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/pokemons")
public class PokemonController {

    private List<String> listaPokemons = new ArrayList<>();

    //http://localhost:8080/pokemons/contagem
    @GetMapping()
    public String contagem() {
        return String.format("A lista possui %s pokemons cadastrados!", listaPokemons.size());
    }

    //http://localhost:8080/pokemons/cadastrar/blablabla
    @GetMapping("/cadastrar/{nomePokemon}")
        public String cadastrar(@PathVariable String nomePokemon) {
            listaPokemons.add(nomePokemon);
            return "Pokemon cadastrado com sucesso!";
        }

    //http://localhost:8080/pokemons/recuperar/[indice]
    @GetMapping("/recuperar/{indice}")
    public String recuperar(@PathVariable int indice) {
        if (verificarIndiceValido(indice))
            return "Pokemon não encontrado!";
        else
            return listaPokemons.get(indice);
    }

    //http://localhost:8080/pokemons/remover/[indice]
    @GetMapping("/remover/{indice}")
    public String remover(@PathVariable int indice) {
        if (verificarIndiceValido(indice))
            return "Pokemon não encontrado!";
        else
            listaPokemons.remove(indice);
        return "Pokemon removido com sucesso!";
    }

    //http://localhost:8080/pokemons/atualizar/[indice]/[novoPokemn]
    @GetMapping("/atualizar/{indice}/{novoPokemon}")
    public String atualizar(@PathVariable int indice, @PathVariable String novoPokemon) {
        if (verificarIndiceValido(indice))
            return "Pokemon não existe!";
        else
            listaPokemons.set(indice, novoPokemon);
        return "Pokemon atualizado com sucesso!";
    }

    private boolean verificarIndiceValido(int indice) {
        if (listaPokemons.size() -1 >= indice && indice >=0) {
            return true;
        }
        return false;
    }

    //http://localhost:8080/pokemons/listar
    @GetMapping("/listar")
    public String listar() {
//        String lista = "";
//            lista += pokemon + "\n";
//        }
//        return lista;

        return listaPokemons.stream().map(pokemon -> pokemon + "\n").reduce("", String::concat);
    }

}
