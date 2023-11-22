package school.sptech.relacionameto1.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

// A anotação @Entity indica que esta classe é uma entidade JPA (Java Persistence API),
// ou seja, ela representa uma tabela no banco de dados.
@Entity
@Getter // Anotação do Lombok que gera métodos getter para todos os campos da classe.
@Setter // Anotação do Lombok que gera métodos setter para todos os campos da classe.
public class Consulta {

    // A anotação @Id indica que este campo é a chave primária da tabela no banco de dados.
    @Id
    // A anotação @GeneratedValue com a estratégia IDENTITY indica que o banco de dados
    // vai se encarregar de gerar o valor para este campo automaticamente quando um novo registro for inserido.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // ID da consulta

    private String nomePaciente; // Nome do paciente que terá a consulta

    // Campo para armazenar a data e a hora da consulta.
    private LocalDateTime dataHora;

    private String status; // Status da consulta (por exemplo, "Agendada", "Concluída", "Cancelada", etc.)

    // A anotação @ManyToOne indica que há um relacionamento "muitos para um" entre Consulta e Medico.
    // Isto é, várias consultas podem ser associadas a um único médico, mas cada consulta está associada a apenas um médico.
    // Na tabela Consulta no banco de dados, haverá uma coluna chamada "medico_id" que armazenará o ID do médico
    // associado a essa consulta.
    @ManyToOne
    private Medico medico;
}