package school.sptech.atividade1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import school.sptech.atividade1.controller.dto.UsuarioCreateRequestDto;
import school.sptech.atividade1.controller.dto.UsuarioResponseDto;
import school.sptech.atividade1.controller.dto.UsuarioSimpleResponse;
import school.sptech.atividade1.utils.UsuarioCreateRequestDtoUtil;
import school.sptech.atividade1.utils.UsuarioResponseDtoUtil;
import school.sptech.atividade1.utils.UsuarioSimpleResponseDtoUtil;

import java.util.List;

@DisplayName("1. Fidelidade a especificação")
public class FidelidadeTests {

    @Nested
    @DisplayName("1.3 UsuarioResponseDto")
    class UsuarioResponseDtoTests {

        @Nested
        @DisplayName("Todos os atributos de Usuário devem respeitar os nomes especificados")
        class TodosAtributosProdutosComoEspecificadosTests {

            @Test
            @DisplayName("1. Deve ter os campos id, nomeCompleto, documento, dataNascimento, contato e idade")
            void test1() {

                List<String> fields = List.of(
                        UsuarioResponseDtoUtil.USUARIO_RESPONSE_DTO_ID,
                        UsuarioResponseDtoUtil.USUARIO_RESPONSE_DTO_NOME_COMPLETO,
                        UsuarioResponseDtoUtil.USUARIO_RESPONSE_DTO_DOCUMENTO,
                        UsuarioResponseDtoUtil.USUARIO_RESPONSE_DTO_DATA_NASCIMENTO,
                        UsuarioResponseDtoUtil.USUARIO_RESPONSE_DTO_CONTATO
                );

                Class<UsuarioResponseDto> clazz = UsuarioResponseDto.class;

                for (String atributo : fields) {
                    Assertions.assertDoesNotThrow(() -> {
                        clazz.getDeclaredField(atributo);
                    });
                }
            }
        }
    }

    @Nested
    @DisplayName("1.2 UsuarioSimpleResponseDto")
    class UsuarioSimpleResponseDtoTests {

        @Nested
        @DisplayName("Todos os atributos de Usuário devem respeitar os nomes especificados")
        class TodosAtributosProdutosComoEspecificadosTests {

            @Test
            @DisplayName("1. Deve ter os campos id e nomeCompleto")
            void test1() {

                List<String> fields = List.of(
                        UsuarioSimpleResponseDtoUtil.USUARIO_SIMPLE_RESPONSE_DTO_ID,
                        UsuarioSimpleResponseDtoUtil.USUARIO_SIMPLE_RESPONSE_DTO_NOME_COMPLETO
                );

                Class<UsuarioSimpleResponse> clazz = UsuarioSimpleResponse.class;

                for (String atributo : fields) {
                    Assertions.assertDoesNotThrow(() -> {
                        clazz.getDeclaredField(atributo);
                    });
                }
            }
        }

    }

    @Nested
    @DisplayName("1.1 UsuarioCreateRequestDto")
    class UsuarioCreateRequestDtoTests {

        @Nested
        @DisplayName("Todos os atributos de Usuário devem respeitar os nomes especificados")
        class TodosAtributosProdutosComoEspecificadosTests {

            @Test
            @DisplayName("1. Deve ter os campos nome, sobrenome, cpf, email, dataNascimento")
            void test1() {

                List<String> fields = List.of(
                        UsuarioCreateRequestDtoUtil.USUARIO_CREATE_REQUEST_DTO_NOME,
                        UsuarioCreateRequestDtoUtil.USUARIO_CREATE_REQUEST_DTO_SOBRENOME,
                        UsuarioCreateRequestDtoUtil.USUARIO_CREATE_REQUEST_DTO_CPF,
                        UsuarioCreateRequestDtoUtil.USUARIO_CREATE_REQUEST_DTO_EMAIL,
                        UsuarioCreateRequestDtoUtil.USUARIO_CREATE_REQUEST_DTO_DATA_NASCIMENTO
                );

                Class<UsuarioCreateRequestDto> clazz = UsuarioCreateRequestDto.class;

                for (String atributo : fields) {
                    Assertions.assertDoesNotThrow(() -> {
                        clazz.getDeclaredField(atributo);
                    });
                }
            }
        }
    }
}
