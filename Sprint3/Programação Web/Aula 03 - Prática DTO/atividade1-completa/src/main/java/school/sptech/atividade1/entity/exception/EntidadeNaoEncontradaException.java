package school.sptech.atividade1.entity.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseStatus;

// NAO ALTERAR ESTE ARQUIVO
@ResponseStatus(HttpStatus.NOT_FOUND)
public class EntidadeNaoEncontradaException extends RuntimeException {

    public EntidadeNaoEncontradaException(String message) {
        super("Entidade %s não encontrada".formatted(message));
    }
}
