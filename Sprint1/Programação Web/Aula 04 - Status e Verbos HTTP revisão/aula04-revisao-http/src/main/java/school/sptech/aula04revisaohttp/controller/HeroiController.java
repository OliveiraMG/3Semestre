package school.sptech.aula04revisaohttp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.aula04revisaohttp.entity.Heroi;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/herois")
public class HeroiController {

  private List<Heroi> herois = new ArrayList<>();

  // GET - http://localhost:8080/herois
  @GetMapping
  public ResponseEntity<List<Heroi>> listar() {
    if (herois.isEmpty()) {
      return ResponseEntity.status(204).build();
    } else {
      return ResponseEntity.status(200).body(herois);
    }
  }

  // GET - http://localhost:8080/herois/0
  @GetMapping("/{indice}")
  public ResponseEntity<Heroi> consultarPorIndice(@PathVariable int indice) {
    if(!isIndiceValido(indice)) {
      return ResponseEntity.status(404).build();
    } else {
      return ResponseEntity.status(200).body(herois.get(indice));
    }
  }

  // POST - http://localhost:8080/herois
  @PostMapping
  public ResponseEntity<Heroi> cadastrar(@RequestBody Heroi heroiNovo) {
    if (heroiNovo.getNome().length() <= 3 || heroiNovo.getNome().isBlank()) {
      return ResponseEntity.status(400).build();
    } else if (heroiNovo.getHabilidade().length() <= 3 || heroiNovo.getHabilidade().isBlank()) {
      return ResponseEntity.status(400).build();
    } else if (heroiNovo.getIdade() <= 0) {
      return ResponseEntity.status(400).build();
    } else if (heroiNovo.getForca() <= 0 && heroiNovo.getForca() > 100) {
      return ResponseEntity.status(400).build();
    } else {
      herois.add(heroiNovo);
      return ResponseEntity.status(201).body(heroiNovo);
    }
  }

  // PUT - http://localhost:8080/herois/0
  @PutMapping("/{indice}")
  public ResponseEntity<Heroi> editarHeroiPorIndice(@PathVariable int indice, @RequestBody Heroi heroiEditado) {
    if (!isIndiceValido(indice)) {
      return ResponseEntity.status(404).build();
    } else {
      if (heroiEditado.getNome().length() <= 3 || heroiEditado.getNome().isBlank()) {
        return ResponseEntity.status(400).build();
      } else if (heroiEditado.getHabilidade().length() <= 3 || heroiEditado.getHabilidade().isBlank()) {
        return ResponseEntity.status(400).build();
      } else if (heroiEditado.getIdade() <= 0) {
        return ResponseEntity.status(400).build();
      } else if (heroiEditado.getForca() <= 0 && heroiEditado.getForca() > 100) {
        return ResponseEntity.status(400).build();
      } else {
        herois.set(indice, heroiEditado);
        return ResponseEntity.status(200).body(heroiEditado);
      }
    }
  }

  @DeleteMapping("/{indice}")
    public ResponseEntity<Void> deletarHeroiPorIndice(@PathVariable int indice) {
        if (!isIndiceValido(indice)) {
        return ResponseEntity.status(404).build();
        } else {
        herois.remove(indice);
        return ResponseEntity.status(204).build();
        }
    }

  public boolean isIndiceValido(int indice) {
    return indice >= 0 && indice < herois.size();
  }
}
