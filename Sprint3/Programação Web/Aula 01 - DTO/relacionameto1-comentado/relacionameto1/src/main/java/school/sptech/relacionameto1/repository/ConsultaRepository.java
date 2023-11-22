package school.sptech.relacionameto1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import school.sptech.relacionameto1.entity.Consulta;

import java.util.List;

/**
 * A interface ConsultaRepository estende JpaRepository, fornecendo facilidades para CRUD (Create, Read, Update, Delete)
 * e outras operações padrão de banco de dados para a entidade Consulta.
 *
 * Em contextos de relações One-to-Many e Many-to-One (como entre Consulta e Medico), o Spring Data JPA
 * oferece meios simplificados para acessar e consultar dados relacionados. Além do mais,
 * a combinação da convenção de nomes e as anotações fornecem uma maneira poderosa,
 * porém simples, de definir e executar consultas.
 *
 * Vantagens:
 *
 * 1. **Consultas Baseadas em Convenção**: Como no método 'findByMedicoNomeContainsIgnoreCase',
 *    onde apenas pelo nome do método, o Spring Data JPA entende que deve buscar consultas com base no nome do médico,
 *    considerando a insensibilidade a maiúsculas/minúsculas.
 *
 * 2. **Customização com @Query**: Permite definir consultas JPQL específicas para satisfazer requisitos mais complexos,
 *    como demonstrado no método 'buscarPorCrm'. Isso oferece maior flexibilidade ao recuperar dados.
 *
 * 3. **Facilidade de Acesso a Entidades Relacionadas**: Não é necessário fazer joins explícitos ou outras operações
 *    complexas. O mapeamento ORM cuida dos detalhes relacionais, permitindo que se concentre na lógica de negócios.
 *
 * 4. **Gerenciamento Automático de Joins**: Quando se consulta dados relacionados, o Spring Data JPA gerencia
 *    automaticamente as operações de "join" nos bastidores. Isso significa que, mesmo que você não veja um "join" explícito,
 *    a estrutura ORM está cuidando disso para recuperar as informações relacionadas de forma eficiente.
 */
public interface ConsultaRepository extends JpaRepository<Consulta, Integer> {

    /**
     * Busca todas as consultas onde o nome do médico contém a string fornecida, ignorando a diferenciação
     * entre maiúsculas e minúsculas.
     *
     * @param nome - Parte do nome do médico.
     * @return Lista de Consultas com médicos que correspondem ao critério de busca.
     */
    List<Consulta> findByMedicoNomeContainsIgnoreCase(String nome);

    /**
     * Busca todas as consultas associadas a um médico com o CRM especificado.
     *
     * A anotação @Query permite definir uma consulta JPQL customizada. Aqui, a consulta busca
     * por consultas cujo médico tem o CRM fornecido como parâmetro.
     *
     * @param codigo - CRM do médico.
     * @return Lista de Consultas associadas ao médico com o CRM fornecido.
     */
    @Query("SELECT c FROM Consulta c WHERE c.medico.crm = :codigo") // named paremeter
    List<Consulta> buscarPorCrm(String codigo);
}