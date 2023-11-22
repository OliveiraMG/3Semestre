package school.sptech.atividade1.controller.dto.mapper;

import school.sptech.atividade1.controller.dto.UsuarioCreateRequestDto;
import school.sptech.atividade1.controller.dto.UsuarioResponseDto;
import school.sptech.atividade1.controller.dto.UsuarioSimpleResponse;
import school.sptech.atividade1.entity.Usuario;

public class UsuarioMapper {

    public static Usuario toEntity(UsuarioCreateRequestDto dto) {
        Usuario usuario = new Usuario();
        usuario.setNome(dto.getNome());
        usuario.setSobrenome(dto.getSobrenome());
        usuario.setCpf(dto.getCpf());
        usuario.setEmail(dto.getEmail());
        usuario.setDataNascimento(dto.getDataNascimento());

        return usuario;
    }

    public static UsuarioResponseDto toUsuarioReponseDto(Usuario entity) {
        UsuarioResponseDto dto = new UsuarioResponseDto();
        dto.setId(entity.getId());
        dto.setNomeCompleto(entity.getNome() + " " + entity.getSobrenome());
        dto.setDocumento(entity.getCpf());
        dto.setDataNascimento(entity.getDataNascimento());
        dto.setContato(entity.getEmail());

        return dto;
    }

    public static UsuarioSimpleResponse toUsuarioSimpleResponse(Usuario entity) {
        UsuarioSimpleResponse dto = new UsuarioSimpleResponse();
        dto.setId(entity.getId());
        dto.setNomeCompleto(entity.getNome() + " " + entity.getSobrenome());

        return dto;
    }
}
