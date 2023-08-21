package school.sptech.aula02nivelamento;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/frases")
public class FraseController {

    private List<String> listaFrases = new ArrayList<>();


    //http://localhost:8080/frases/contagem
    @GetMapping("/contagem")
    public String contagem() {
        return String.format("A lista possui %s frases cadastradas!", listaFrases.size());
    }

    //http://localhost:8080/frases
    @GetMapping
    public String frase() {
        return "A persistência é o caminho do êxito.";
    }

    //http://localhost:8080/frases/outra-frase
    @GetMapping("/outra-frase")
    public  String outraFase() {
        return "Outra frase";
    }

    //http://localhost:8080/frases/personalizada/blablabla
    @GetMapping("/personalizada/{nome}")
    public String personalizada(@PathVariable String nome) {
        return String.format("Boa tarde %s. Voce está muito bonito(a) como sempre!", nome);
    }

    //http://localhost:8080/frases/personalizada/Matheus/Oliveira
    @GetMapping("/personalizada/{nome}/{sobrenome}")
    public String personalizada(@PathVariable String nome, @PathVariable String sobrenome) {
        return String.format("Boa tarde %s %s. Voce está muito bonito(a) como sempre!", nome, sobrenome);
    }
}
