package school.sptech.atividade1.controller.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

//FIXME: Completar a classe
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioCreateRequestDto {

    @NotBlank
    private String nome;
    @NotBlank
    private String sobrenome;
    @CPF
    private String cpf;
    @Email
    private String email;
    @Past
    private LocalDate dataNascimento;

}
