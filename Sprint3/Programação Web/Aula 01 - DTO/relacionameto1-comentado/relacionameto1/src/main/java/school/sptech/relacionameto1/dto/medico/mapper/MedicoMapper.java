package school.sptech.relacionameto1.dto.medico.mapper;

import school.sptech.relacionameto1.dto.medico.ConsultaMedicoDto;
import school.sptech.relacionameto1.dto.medico.MedicoConsultaDto;
import school.sptech.relacionameto1.entity.Consulta;
import school.sptech.relacionameto1.entity.Medico;

import java.util.List;

/**
 * A classe MedicoMapper é responsável por mapear (ou converter) entidades (como Medico e Consulta)
 * em seus respectivos Data Transfer Objects (DTOs) e vice-versa.
 *
 * Os mappers são comumente usados para:
 *
 * 1. Separar a representação de dados usada na camada de persistência daquela usada na camada de apresentação ou API.
 * 2. Evitar exposição direta das entidades de domínio ou entidade à camada de apresentação.
 * 3. Prevenir problemas relacionados ao carregamento preguiçoso (lazy loading) em frameworks ORM,
 *    já que a serialização direta das entidades pode levar a erros ou comportamentos inesperados.
 * 4. Personalizar a forma como os dados são apresentados ao usuário final ou a outros sistemas,
 *    selecionando ou transformando apenas as informações relevantes.
 * 5. Facilitar a manutenção, já que mudanças no domínio ou na camada de apresentação podem ser gerenciadas
 *    adaptando-se os mappers, sem afetar outras partes do sistema.
 *
 * Os métodos desta classe são usados para converter entre a representação de entidade e a representação DTO.
 */
public class MedicoMapper {

    /**
     * Converte uma entidade Medico em seu respectivo DTO MedicoConsultaDto.
     * @param medicoEntidade - Entidade Medico a ser convertida.
     * @return MedicoConsultaDto - DTO resultante da conversão.
     */
    public static MedicoConsultaDto toMedicoConsultaDto(Medico medicoEntidade) {

        if (medicoEntidade == null) {
            return null;
        }

        MedicoConsultaDto medicoDto = new MedicoConsultaDto();

        medicoDto.setId(medicoEntidade.getId());
        medicoDto.setCrm(medicoEntidade.getCrm());
        medicoDto.setNome(medicoEntidade.getNome());
        medicoDto.setEspecialidade(medicoEntidade.getEspecialidade());

        if (!medicoEntidade.getConsultas().isEmpty()){

            List<Consulta> consultas = medicoEntidade.getConsultas();

            // STREAM API
            List<ConsultaMedicoDto> consultasDtos = consultas.stream()
                    .map(MedicoMapper::toConsultaDto)
                    .toList();

            medicoDto.setConsultas(consultasDtos);
        }

        return medicoDto;
    }

    /**
     * Converte uma entidade Consulta em seu respectivo DTO ConsultaMedicoDto.
     * @param consultaEntidade - Entidade Consulta a ser convertida.
     * @return ConsultaMedicoDto - DTO resultante da conversão.
     */
    public static ConsultaMedicoDto toConsultaDto(Consulta consultaEntidade) {

        if (consultaEntidade == null) return null;

        ConsultaMedicoDto consultaMedicoDto = new ConsultaMedicoDto();

        consultaMedicoDto.setId(consultaEntidade.getId());
        consultaMedicoDto.setNomePaciente(consultaEntidade.getNomePaciente());
        consultaMedicoDto.setDataHora(consultaEntidade.getDataHora());
        consultaMedicoDto.setStatus(consultaEntidade.getStatus());

        return consultaMedicoDto;
    }

}
