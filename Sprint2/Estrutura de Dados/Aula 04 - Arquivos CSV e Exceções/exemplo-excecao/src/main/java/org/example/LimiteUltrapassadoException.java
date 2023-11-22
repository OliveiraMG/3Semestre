package org.example;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/* Boas práticas na criação de uma classe personalizada de exceção:
   - Nome da classe deve terminar com a palavra Exception
   - A classe de exceção deve ser herdeira de Exception
 */
public class LimiteUltrapassadoException extends Exception {
    String dataHora;

    /* Boa prática: fazer o construtor chamar o construtor da
       classe Exception, chamando o super
     */
    public LimiteUltrapassadoException(String msg) {
        super(msg);
        dataHora = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
    }

}
