package school.sptech.relacionameto1.dto.medico;

import lombok.Data;

import java.util.List;

@Data  // Anotação do Lombok que gera automaticamente os métodos getter, setter, equals, hashCode e toString.
/**
 * A classe MedicoConsultaDto é um Data Transfer Object (DTO).
 *
 * Finalidade de um DTO:
 * 1. Transportar dados entre subsistemas ou camadas de uma aplicação, como entre a camada de serviço e a camada de apresentação/API.
 * 2. Simplificar e modelar apenas os dados específicos que se deseja transmitir.
 * 3. Evitar a exposição direta da estrutura e lógica das entidades de domínio ou do banco de dados.
 * 4. Combina informações de várias entidades ou fontes para uma estruturação conveniente para consumo.
 * 5. Desacoplar a camada de apresentação/API da lógica de negócios e do modelo de domínio.
 * 6. Melhorar a performance ao limitar a quantidade de dados transferidos entre operações.
 *
 * Adicionalmente, ao usar DTOs em sistemas com mapeamentos bidirecionais (como relações entre entidades JPA),
 * é possível prevenir erros de StackOverflowError durante serializações, pois o DTO pode ser projetado para não
 * seguir relações infinitas entre entidades relacionadas.
 */
public class MedicoConsultaDto {

    private Integer id;

    private String crm;

    private String nome;

    private String especialidade;

    private List<ConsultaMedicoDto> consultas;
}