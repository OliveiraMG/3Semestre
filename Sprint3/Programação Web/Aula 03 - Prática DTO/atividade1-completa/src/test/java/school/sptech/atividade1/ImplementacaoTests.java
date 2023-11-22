package school.sptech.atividade1;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import school.sptech.atividade1.controller.UsuarioController;
import school.sptech.atividade1.entity.Usuario;
import school.sptech.atividade1.entity.exception.EntidadeNaoEncontradaException;
import school.sptech.atividade1.factory.UsuarioCreateRequestDtoFactory;
import school.sptech.atividade1.factory.UsuarioResponseDtoFactory;
import school.sptech.atividade1.service.UsuarioService;
import school.sptech.atividade1.utils.UsuarioResponseDtoUtil;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.time.Period;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("2. Implementação dos métodos")
public class ImplementacaoTests {

    @Nested
    @DisplayName("2.3 Endpoint: POST /usuarios")
    @WebMvcTest(UsuarioController.class)
    class UsuarioCreateRequestDtoTest {

        @Autowired
        private MockMvc mockMvc;

        @Autowired
        private ObjectMapper objectMapper;

        @MockBean
        private UsuarioService service;

        @Test
        @DisplayName("Teste de criação de usuário - campos corretos")
        void deveRetornarStatus201ObjetoCriacaoDto() throws Exception {

            Usuario usuarioEntidade = new Usuario(
                    1,
                    "Paulo",
                    "Muniz",
                    "87395272048",
                    "paulo@gmail.com",
                    LocalDate.of(1999, 12, 1));

            Object usuarioCriacaoRfl = UsuarioCreateRequestDtoFactory.getInstance(
                    "Paulo",
                    "Muniz",
                    "87395272048",
                    "paulo@gmail.com",
                    LocalDate.of(1999, 12, 1));


            when(service.salvar(Mockito.any())).thenReturn(usuarioEntidade);

            String objectString = objectMapper.writeValueAsString(usuarioCriacaoRfl);

            MockHttpServletResponse response = mockMvc.perform(post("/usuarios")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectString))
                    .andExpect(status().isCreated())
                    .andReturn()
                    .getResponse();


            Map<String, Object> fieldMap = objectMapper.readValue(response.getContentAsString(),
                    new TypeReference<>() {
                    });

            // RFL CONFIG
            Object dto = UsuarioResponseDtoFactory.getInstance(fieldMap);

            assertResponse(usuarioEntidade, dto);
        }

        @Test
        @DisplayName("Teste de criação de usuário - Nome inválido")
        void deveRetornarStatus400() throws Exception {

            Object usuarioCriacaoRfl = UsuarioCreateRequestDtoFactory.getInstance(
                    null,
                    "Muniz",
                    "87395272048",
                    "paulo@gmail.com",
                    LocalDate.of(1999, 12, 1));

            String objectString = objectMapper.writeValueAsString(usuarioCriacaoRfl);

            MockHttpServletResponse response = mockMvc.perform(post("/usuarios")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectString))
                    .andExpect(status().is4xxClientError())
                    .andReturn()
                    .getResponse();
        }

        @Test
        @DisplayName("Teste de criação de usuário - CPF inválido")
        void deveRetornarStatus400QuandoCpfForInvalido() throws Exception {

            Object usuarioCriacaoRfl = UsuarioCreateRequestDtoFactory.getInstance(
                    "Manoel",
                    "Almeida",
                    "12312372048",
                    "paulo@gmail.com",
                    LocalDate.of(1999, 12, 1));


            String objectString = objectMapper.writeValueAsString(usuarioCriacaoRfl);

            MockHttpServletResponse response = mockMvc.perform(post("/usuarios")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectString))
                    .andExpect(status().is4xxClientError())
                    .andReturn()
                    .getResponse();
        }
    }

    @Nested
    @DisplayName("2.2 Enpoint: GET /usuarios")
    @WebMvcTest(UsuarioController.class)
    class GetListUsuarioResponseDtoTest {

        @Autowired
        private MockMvc mockMvc;

        @Autowired
        private ObjectMapper objectMapper;

        @MockBean
        private UsuarioService service;

        @Test
        @DisplayName("Deve retornar lista de usuários")
        void deveRetornarListaRespostaDto() throws Exception {

            List<Usuario> usuariosEntidade = List.of(new Usuario(
                    1,
                    "Paulo",
                    "Muniz",
                    "87395272048",
                    "paulo@gmail.com",
                    LocalDate.of(1999, 12, 1)));

            when(service.listar()).thenReturn(usuariosEntidade);

            String contentJson = mockMvc.perform(get("/usuarios"))
                    .andExpect(status().isOk())
                    .andReturn()
                    .getResponse()
                    .getContentAsString();

            verify(service, times(1)).listar();

            List<Map<String, Object>> listaDtos = objectMapper.readValue(
                    contentJson,
                    new TypeReference<>() {
                    }
            );

            List<Object> dtosRfl = listaDtos.stream()
                    .map(dto -> {
                        try {
                            return UsuarioResponseDtoFactory.getInstance(dto);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        return null;
                    }).filter(Objects::nonNull).toList();


            Assertions.assertFalse(listaDtos.isEmpty());


            for (int i = 0; i < dtosRfl.size(); i++) {
                Object dto = dtosRfl.get(i);
                Usuario usuarioEntidade = usuariosEntidade.get(i);
                assertResponse(usuarioEntidade, dto);
            }
        }

        @Test
        @DisplayName("Deve retornar código 204 quando lista vazia")
        void deveRetornarStatus204ListaVazia() throws Exception {

            when(service.listar()).thenReturn(Collections.emptyList());

            mockMvc.perform(get("/usuarios"))
                    .andExpect(status().isNoContent());

            verify(service, times(1)).listar();
        }
    }

    @Nested
    @DisplayName("2.1 Endpoint: GET /usuarios/id")
    @WebMvcTest(UsuarioController.class)
    class GetIdUsuarioResponseDtoTest {

        @Autowired
        private MockMvc mockMvc;

        @Autowired
        private ObjectMapper objectMapper;

        @MockBean
        private UsuarioService service;

        @Test
        @DisplayName("Deve retornar usuário por id corretamente")
        void deveRetornarUsuarioEncontrado() throws Exception {

            Usuario usuarioEntidade = new Usuario(
                    1,
                    "Paulo",
                    "Muniz",
                    "87395272048",
                    "paulo@gmail.com",
                    LocalDate.of(1999, 12, 1));

            when(service.buscarPorId(1)).thenReturn(usuarioEntidade);

            MockHttpServletResponse response = mockMvc.perform(get("/usuarios/1"))
                    .andExpect(status().isOk())
                    .andReturn()
                    .getResponse();

            verify(service, times(1)).buscarPorId(1);

            Map<String, Object> fieldMap = objectMapper.readValue(response.getContentAsString(),
                    new TypeReference<Map<String, Object>>() {
                    });

            Object dto = UsuarioResponseDtoFactory.getInstance(fieldMap);

            assertResponse(usuarioEntidade, dto);
        }

        @Test
        @DisplayName("Deve retornar código 404 quando usuário não encontrado")
        void deveRetornarStatus404NaoEncontrado() throws Exception {

            when(service.buscarPorId(1)).thenThrow(EntidadeNaoEncontradaException.class);

            mockMvc.perform(get("/usuarios/1"))
                    .andExpect(status().isNotFound());

            verify(service, times(1)).buscarPorId(1);
        }
    }

    public void assertResponse(Usuario entidade, Object dto) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, NoSuchFieldException {

        Field campoNomeCompleto = dto.getClass().getDeclaredField(UsuarioResponseDtoUtil.USUARIO_RESPONSE_DTO_NOME_COMPLETO);
        Field campoDocumento = dto.getClass().getDeclaredField(UsuarioResponseDtoUtil.USUARIO_RESPONSE_DTO_DOCUMENTO);
        Field campoContato = dto.getClass().getDeclaredField(UsuarioResponseDtoUtil.USUARIO_RESPONSE_DTO_CONTATO);
        Field campoDataNascimento = dto.getClass().getDeclaredField(UsuarioResponseDtoUtil.USUARIO_RESPONSE_DTO_DATA_NASCIMENTO);

        Method getIdade = dto.getClass().getDeclaredMethod(UsuarioResponseDtoUtil.METODO_USUARIO_RESPONSE_DTO_IDADE);

        campoNomeCompleto.setAccessible(true);
        campoDocumento.setAccessible(true);
        campoContato.setAccessible(true);
        campoDataNascimento.setAccessible(true);

        String dtoNomeCompleto = (String) campoNomeCompleto.get(dto);
        String dtoDocumento = (String) campoDocumento.get(dto);
        String dtoContato = (String) campoContato.get(dto);
        LocalDate dtoDataNascimento = (LocalDate) campoDataNascimento.get(dto);
        int idadeDto = (int) getIdade.invoke(dto);

        final String entidadeNomeCompleto = "%s %s".formatted(entidade.getNome(), entidade.getSobrenome());

        Assertions.assertEquals(dtoNomeCompleto, entidadeNomeCompleto, "Nome completo deve ser igual.");
        Assertions.assertEquals(dtoDocumento, entidade.getCpf(), "Documento deve ser igual.");
        Assertions.assertEquals(dtoContato, entidade.getEmail(), "Contato deve ser igual.");
        Assertions.assertEquals(Period.between(dtoDataNascimento, LocalDate.now()).getYears(), idadeDto, "Idade deve ser igual.");
    }
}