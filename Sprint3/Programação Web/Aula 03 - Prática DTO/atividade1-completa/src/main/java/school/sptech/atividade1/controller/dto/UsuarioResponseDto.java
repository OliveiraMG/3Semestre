package school.sptech.atividade1.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.Period;

//FIXME: Completar a classe
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioResponseDto {

    private int id;
    private String nomeCompleto;
    private String documento;
    private LocalDate dataNascimento;
    private String contato;


    //FIXME: Implementar lógica para calcular a idade
    public int getIdade() {
        LocalDate hoje = LocalDate.now();

        Period periodo = Period.between(this.dataNascimento, hoje);

        return periodo.getYears();

//        int idade = hoje.getYear() - this.dataNascimento.getYear();
//
//        if (this.dataNascimento.getMonthValue() > hoje.getMonthValue()) {
//            idade--;
//        } else if (this.dataNascimento.getMonthValue() == hoje.getMonthValue()) {
//            if (this.dataNascimento.getDayOfMonth() > hoje.getDayOfMonth()) {
//                idade--;
//            }
//        }
//
//        return idade;
    }
}
