package school.sptech.aula02nivelamento;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/calculos")
public class CalculoController {

    private int contador;

    // http://localhost:8080/calculos/somar/10/20
    @GetMapping("/somar/{n1}/{n2}")
    public int somar(@PathVariable int n1, @PathVariable int n2) {
        return n1 + n2;
    }

    // http://localhost:8080/calculos/contador
    @GetMapping("/contador")
    public int contador() {
        return ++contador;
    }

    // http://localhost:8080/calculos/outro-contador
    @GetMapping("/outro-contador")
    public int outroContador() {
        return ++contador;
    }

}
