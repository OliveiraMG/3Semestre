package school.sptech.relacionameto1.entity;

// Importações de bibliotecas necessárias

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

// A anotação @Entity indica que esta classe é uma entidade JPA (Java Persistence API),
// ou seja, ela representa uma tabela no banco de dados.
@Entity
@Getter // Anotação do Lombok que gera métodos getter para todos os campos da classe.
@Setter // Anotação do Lombok que gera métodos setter para todos os campos da classe.
public class Medico {

    // A anotação @Id indica que este campo é a chave primária da tabela no banco de dados.
    @Id
    // A anotação @GeneratedValue com a estratégia IDENTITY indica que o banco de dados
    // vai se encarregar de gerar o valor para este campo automaticamente quando um novo registro for inserido.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // ID do médico

    private String crm; // Registro do médico no Conselho Regional de Medicina

    private String nome; // Nome do médico

    private String especialidade; // Especialidade médica do médico (ex: Cardiologia, Pediatria, etc.)

    // A anotação @OneToMany indica que há um relacionamento "um para muitos" entre Medico e Consulta.
    // Isto é, um médico pode ter várias consultas associadas a ele, mas cada consulta está associada a apenas um médico.
    // O atributo "mappedBy" indica que o lado proprietário deste relacionamento é a entidade Consulta (na sua propriedade "medico").
    // Em outras palavras, a tabela Consulta no banco de dados tem uma coluna chamada "medico_id" que estabelece este relacionamento.
    @OneToMany(mappedBy = "medico")
    List<Consulta> consultas; // Lista de consultas associadas a este médico
}