package school.sptech.avaliacaoweb3.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import school.sptech.avaliacaoweb3.entity.EmpresaParceira;
import school.sptech.avaliacaoweb3.factory.EmpresaParceiraMockFactory;
import school.sptech.avaliacaoweb3.service.EmpresaParceiraService;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Testes para o controller: EmpresaParceiraController")
class EmpresaParceiraControllerTest {

    @Mock
    private EmpresaParceiraService empresaParceiraService;

    @InjectMocks
    private EmpresaParceiraController empresaParceiraController;

    @Nested
    @DisplayName("1. Testes para o método findAll")
    public class ListagemTest {

        @Test
        @DisplayName("1.2 cenário: findAll quando não existem empresas deve retornar codigo 204 e corpo vazio")
        public void findAll_quandoNaoExistemEmpresas_retornaCodigo204() {
            // when
            when(empresaParceiraService.findAll()).thenReturn(Arrays.asList());

            // then
            ResponseEntity<List<EmpresaParceira>> response = empresaParceiraController.findAll();

            // assert
            assertEquals(204, response.getStatusCodeValue());
            assertNull(response.getBody());
            verify(empresaParceiraService).findAll();
        }

        @Test
        @DisplayName("1.1 cenário: findAll quando existem empresas deve retornar lista de empresas")
        public void findAll_quandoExistemEmpresas_retornaListaEmpresas() throws NoSuchFieldException, InvocationTargetException, IllegalAccessException, InstantiationException {

            //given
            Object empresaParceiraBusca = EmpresaParceiraMockFactory.getInstance(
                    1,
                    "Empresa Engraçada, S.A.",
                    "27.096.560/0001-48",
                    "Rua dos bobos, 0"
            );

            // when
            when(empresaParceiraService.findAll()).thenReturn(List.of((EmpresaParceira) empresaParceiraBusca));

            // then
            ResponseEntity<List<EmpresaParceira>> response = empresaParceiraController.findAll();

            // assert
            assertFalse(response.getBody().isEmpty());
            assertEquals(200, response.getStatusCodeValue());
            verify(empresaParceiraService).findAll();
        }
    }

    @Nested
    @DisplayName("2. Testes para o método findById")
    public class FindByIdTest {

        @Nested
        @DisplayName("2.2 cenário: findById quando empresa existe deve retornar empresa e código 404 NOT FOUND")
        @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
        public class EmpresaParceiraControllerIntegrationTest {

            @Autowired
            private TestRestTemplate restTemplate;

            @Test
            @DisplayName("Cenário único")
            public void testGetEmpresaParceiraNotFound() {
                ResponseEntity<String> response = restTemplate.getForEntity("/empresas/{id}", String.class, 999);

                assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
            }
        }

        @Test
        @DisplayName("2.1 cenário: findById quando empresa existe deve retornar empresa e código 200 OK")
        void findById_quandoExisteEmpresa_deveRetornarEmpresa() throws NoSuchFieldException, InvocationTargetException, IllegalAccessException, InstantiationException {

            Object empresaParceiraBusca = EmpresaParceiraMockFactory.getInstance(
                    1,
                    "Empresa Engraçada, S.A.",
                    "27.096.560/0001-48",
                    "Rua dos bobos, 0"
            );

            // when
            when(empresaParceiraService.findById(1)).thenReturn((EmpresaParceira) empresaParceiraBusca);

            // then
            ResponseEntity<EmpresaParceira> response = empresaParceiraController.findById(1);

            // assert
            assertEquals(200, response.getStatusCodeValue());
            assertEquals(empresaParceiraBusca, response.getBody());
        }
    }

    @Nested
    @DisplayName("3. Testes para o método save")
    public class SaveTest {

        @Nested
        @DisplayName("3.5 cenário: save quando endereco está inválido deve retornar empresa e código 400 BAD REQUEST")
        @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
        public class EmpresaParceiraControllerIntegrationTestEnderecoInvalido400 {

            @Autowired
            private TestRestTemplate restTemplate;

            @Test
            @DisplayName("Cenário: endereco nulo")
            public void testGetEmpresaParceiraNotFoundEnderecoNulo() throws NoSuchFieldException, InvocationTargetException, IllegalAccessException, InstantiationException {


                Object empresaComEnderecoNulo = EmpresaParceiraMockFactory.getInstance(
                        null,
                        "Empresa Engraçada, S.A.",
                        "27.096.560/0001-48",
                        null
                );

                ResponseEntity<String> response = restTemplate.postForEntity("/empresas", (EmpresaParceira) empresaComEnderecoNulo, String.class);

                assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
            }

            @Test
            @DisplayName("Cenário: endereco em branco")
            public void testGetEmpresaParceiraNotFoundEnderecoBranco() throws NoSuchFieldException, InvocationTargetException, IllegalAccessException, InstantiationException {

                Object empresaComEnderecoBranco = EmpresaParceiraMockFactory.getInstance(
                        null,
                        "Empresa Engraçada, S.A.",
                        "27.096.560/0001-48",
                        " "
                );

                ResponseEntity<String> response = restTemplate.postForEntity("/empresas", (EmpresaParceira) empresaComEnderecoBranco, String.class);


                assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
            }

            @Test
            @DisplayName("Cenário: endereco em vazio")
            public void testGetEmpresaParceiraNotFoundEnderecoVazio() throws NoSuchFieldException, InvocationTargetException, IllegalAccessException, InstantiationException {

                Object empresaComEnderecoVazio = EmpresaParceiraMockFactory.getInstance(
                        null,
                        "Empresa Engraçada, S.A.",
                        "27.096.560/0001-48",
                        ""
                );

                ResponseEntity<String> response = restTemplate.postForEntity("/empresas", (EmpresaParceira) empresaComEnderecoVazio, String.class);

                assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
            }
        }

        @Nested
        @DisplayName("3.4 cenário: save quando nome está inválido deve retornar empresa e código 400 BAD REQUEST")
        @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
        public class EmpresaParceiraControllerIntegrationTestNomeNulo400 {

            @Autowired
            private TestRestTemplate restTemplate;

            @Test
            @DisplayName("Cenário: nome nulo")
            public void testGetEmpresaParceiraBadRequestNomeNulo() throws NoSuchFieldException, InvocationTargetException, IllegalAccessException, InstantiationException {

                Object empresaParceiraCriacaoNulo = EmpresaParceiraMockFactory.getInstance(
                        null,
                        null,
                        "27.096.560/0001-48",
                        "Rua dos bobos, 0"
                );

                ResponseEntity<String> response = restTemplate.postForEntity("/empresas", empresaParceiraCriacaoNulo, String.class);

                assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
            }

            @Test
            @DisplayName("Cenário: nome vazio")
            public void testGetEmpresaParceiraBadRequestNomeVazio() throws NoSuchFieldException, InvocationTargetException, IllegalAccessException, InstantiationException {

                Object empresaParceiraCriacaoNomeVazio = EmpresaParceiraMockFactory.getInstance(
                        null,
                        "",
                        "27.096.560/0001-48",
                        "Rua dos bobos, 0"
                );

                ResponseEntity<String> response = restTemplate.postForEntity("/empresas", empresaParceiraCriacaoNomeVazio, String.class);

                assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
            }

            @Test
            @DisplayName("Cenário: nome branco")
            public void testGetEmpresaParceiraBadRequestNomeBranco() throws NoSuchFieldException, InvocationTargetException, IllegalAccessException, InstantiationException {

                Object empresaParceiraCriacaoNomeBranco = EmpresaParceiraMockFactory.getInstance(
                        null,
                        " ",
                        "27.096.560/0001-48",
                        "Rua dos bobos, 0"
                );

                ResponseEntity<String> response = restTemplate.postForEntity("/empresas", empresaParceiraCriacaoNomeBranco, String.class);

                assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
            }
        }

        @Nested
        @DisplayName("3.3 cenário: save quando cnpj está errado deve retornar empresa e código 400 BAD REQUEST")
        @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
        public class EmpresaParceiraControllerIntegrationTestCnpjErrado400 {

            @Autowired
            private TestRestTemplate restTemplate;

            @Test
            @DisplayName("Cenário único")
            public void testGetEmpresaParceiraNotFound() throws NoSuchFieldException, InvocationTargetException, IllegalAccessException, InstantiationException {

                Object empresaParceiraCriacaoCnpjErrado = EmpresaParceiraMockFactory.getInstance(
                        null,
                        "Empresa Engraçada, S.A.",
                        "37224652000111",
                        "Rua dos bobos, 0"
                );

                ResponseEntity<String> response = restTemplate.postForEntity("/empresas", empresaParceiraCriacaoCnpjErrado, String.class);
                assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
            }
        }

        @Nested
        @DisplayName("3.2 cenário: save quando cnpj já existe deve retornar empresa e código 409 CONFLICT")
        @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
        public class EmpresaParceiraControllerIntegrationTestCnpjExistente409 {

            @Autowired
            private TestRestTemplate restTemplate;

            @Test
            @DisplayName("Cenário único")
            public void testGetEmpresaParceiraNotFound() throws NoSuchFieldException, InvocationTargetException, IllegalAccessException, InstantiationException {

                Object empresaParceiraCriacaoCnpjExistente = EmpresaParceiraMockFactory.getInstance(
                        null,
                        "Empresa Engraçada, S.A.",
                        "37224652000110",
                        "Rua dos bobos, 0"
                );

                ResponseEntity<String> response = restTemplate.postForEntity("/empresas", empresaParceiraCriacaoCnpjExistente, String.class);
                assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
            }
        }

        @Test
        @DisplayName("3.1 cenário: save quando empresa não existe deve retornar empresa e código 200 OK")
        void save_quandoEmpresaNaoExiste_deveRetornarEmpresa() throws NoSuchFieldException, InvocationTargetException, IllegalAccessException, InstantiationException {

            Object empresaParceiraBusca = EmpresaParceiraMockFactory.getInstance(
                    null,
                    "Empresa Engraçada, S.A.",
                    "27.096.560/0001-48",
                    "Rua dos bobos, 0"
            );

            Object empresaParceiraBuscaComId = EmpresaParceiraMockFactory.getInstance(
                    1,
                    "Empresa Engraçada, S.A.",
                    "27.096.560/0001-48",
                    "Rua dos bobos, 0"
            );

            // when
            when(empresaParceiraService.save((EmpresaParceira) empresaParceiraBusca)).thenReturn(((EmpresaParceira) empresaParceiraBuscaComId));

            // then
            ResponseEntity<EmpresaParceira> response = empresaParceiraController.save((EmpresaParceira) empresaParceiraBusca);

            // assert
            assertEquals(200, response.getStatusCodeValue());
            assertTrue(Objects.nonNull(response.getBody()));
        }
    }

    @Nested
    @DisplayName("4. Testes para o método update")
    public class UpdateTest {

        @Nested
        @DisplayName("4.5 cenário: update quando endereco está inválido deve retornar empresa e código 400 BAD REQUEST")
        @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
        public class EmpresaParceiraControllerIntegrationTestEnderecoInvalido400 {

            @Autowired
            private TestRestTemplate restTemplate;

            @Test
            @DisplayName("Teste: Empresa Parceira com Endereço Nulo")
            public void verificaRespostaQuandoEnderecoEmpresaParceiraEhNulo() throws NoSuchFieldException, InvocationTargetException, IllegalAccessException, InstantiationException {

                Object empresaComEnderecoNulo = EmpresaParceiraMockFactory.getInstance(
                        null,
                        "Empresa Engraçada, S.A.",
                        "27.096.560/0001-48",
                        null
                );

                HttpEntity<EmpresaParceira> requestEntity = new HttpEntity<>((EmpresaParceira) empresaComEnderecoNulo);
                ResponseEntity<String> resposta = restTemplate.exchange(
                        "/empresas/{id}",
                        HttpMethod.PUT,
                        requestEntity,
                        String.class,
                        1
                );

                assertEquals(HttpStatus.BAD_REQUEST, resposta.getStatusCode());
            }


            @Test
            @DisplayName("Cenário: endereco em branco")
            public void testGetEmpresaParceiraNotFoundEnderecoBranco() throws NoSuchFieldException, InvocationTargetException, IllegalAccessException, InstantiationException {

                Object empresaComEnderecoBranco = EmpresaParceiraMockFactory.getInstance(
                        null,
                        "Empresa Engraçada, S.A.",
                        "27.096.560/0001-48",
                        " "
                );

                HttpEntity<EmpresaParceira> requestEntity = new HttpEntity<>((EmpresaParceira) empresaComEnderecoBranco);
                ResponseEntity<String> resposta = restTemplate.exchange(
                        "/empresas/{id}",
                        HttpMethod.PUT,
                        requestEntity,
                        String.class,
                        1
                );

                assertEquals(HttpStatus.BAD_REQUEST, resposta.getStatusCode());
            }

            @Test
            @DisplayName("Cenário: endereco em vazio")
            public void testGetEmpresaParceiraNotFoundEnderecoVazio() throws NoSuchFieldException, InvocationTargetException, IllegalAccessException, InstantiationException {

                Object empresaComEnderecoVazio = EmpresaParceiraMockFactory.getInstance(
                        null,
                        "Empresa Engraçada, S.A.",
                        "27.096.560/0001-48",
                        ""
                );


                HttpEntity<EmpresaParceira> requestEntity = new HttpEntity<>((EmpresaParceira) empresaComEnderecoVazio);
                ResponseEntity<String> resposta = restTemplate.exchange(
                        "/empresas/{id}",
                        HttpMethod.PUT,
                        requestEntity,
                        String.class,
                        1
                );

                assertEquals(HttpStatus.BAD_REQUEST, resposta.getStatusCode());
            }
        }

        @Nested
        @DisplayName("4.4 cenário: update quando nome está inválido deve retornar empresa e código 400 BAD REQUEST")
        @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
        public class EmpresaParceiraControllerIntegrationTestNomeNulo400 {

            @Autowired
            private TestRestTemplate restTemplate;

            @Test
            @DisplayName("Cenário: nome nulo")
            public void testGetEmpresaParceiraBadRequestNomeNulo() throws NoSuchFieldException, InvocationTargetException, IllegalAccessException, InstantiationException {

                Object empresaParceiraCriacaoNomeNulo = EmpresaParceiraMockFactory.getInstance(
                        null,
                        null,
                        "27.096.560/0001-48",
                        "Rua dos bobos, 0"
                );

                ResponseEntity<String> response = restTemplate.postForEntity("/empresas", empresaParceiraCriacaoNomeNulo, String.class);

                assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
            }

            @Test
            @DisplayName("Cenário: nome vazio")
            public void testGetEmpresaParceiraBadRequestNomeVazio() throws NoSuchFieldException, InvocationTargetException, IllegalAccessException, InstantiationException {

                Object empresaParceiraCriacaoNomeVazio = EmpresaParceiraMockFactory.getInstance(
                        null,
                        "",
                        "27.096.560/0001-48",
                        "Rua dos bobos, 0"
                );

                ResponseEntity<String> response = restTemplate.postForEntity("/empresas", empresaParceiraCriacaoNomeVazio, String.class);

                assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
            }

            @Test
            @DisplayName("Cenário: nome branco")
            public void testGetEmpresaParceiraBadRequestNomeBranco() throws NoSuchFieldException, InvocationTargetException, IllegalAccessException, InstantiationException {

                Object empresaParceiraCriacaoNomeBranco = EmpresaParceiraMockFactory.getInstance(
                        null,
                        "",
                        "27.096.560/0001-48",
                        "Rua dos bobos, 0"
                );

                ResponseEntity<String> response = restTemplate.postForEntity("/empresas", empresaParceiraCriacaoNomeBranco, String.class);

                assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
            }
        }

        @Nested
        @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
        @DisplayName("4.3 cenário: update quando cnpj está errado deve retornar empresa e código 400 BAD REQUEST")
        public class EmpresaParceiraControllerIntegrationTestCnpjErrado400 {

            @Autowired
            private TestRestTemplate restTemplate;

            @Test
            @DisplayName("CNPJ Inválido")
            public void testGetEmpresaParceiraNotFound() throws NoSuchFieldException, InvocationTargetException, IllegalAccessException, InstantiationException {

                Object empresaParceiraCriacaoCnpjErrado = EmpresaParceiraMockFactory.getInstance(
                        null,
                        "Empresa Engraçada, S.A.",
                        "37224652000111",
                        "Rua dos bobos, 0"
                );

                HttpEntity<EmpresaParceira> requestEntity = new HttpEntity<>((EmpresaParceira) empresaParceiraCriacaoCnpjErrado);
                ResponseEntity<String> resposta = restTemplate.exchange(
                        "/empresas/{id}",
                        HttpMethod.PUT,
                        requestEntity,
                        String.class,
                        1
                );

                assertEquals(HttpStatus.BAD_REQUEST, resposta.getStatusCode());
            }

            @Test
            @DisplayName("CNPJ já existente")
            public void testGetEmpresaParceiraNotFoundCnpjExistente() throws NoSuchFieldException, InvocationTargetException, IllegalAccessException, InstantiationException {

                Object empresaParceiraCriacaoCnpjExistente = EmpresaParceiraMockFactory.getInstance(
                        null,
                        "Empresa Engraçada, S.A.",
                        "37224652000110",
                        "Rua dos bobos, 0"
                );

                HttpEntity<EmpresaParceira> requestEntity = new HttpEntity<>((EmpresaParceira) empresaParceiraCriacaoCnpjExistente);
                ResponseEntity<String> resposta = restTemplate.exchange(
                        "/empresas/{id}",
                        HttpMethod.PUT,
                        requestEntity,
                        String.class,
                        1
                );

                assertEquals(HttpStatus.CONFLICT, resposta.getStatusCode());
            }

            @Test
            @DisplayName("CNPJ nulo")
            public void testGetEmpresaParceiraNotFoundCnpjNulo() throws NoSuchFieldException, InvocationTargetException, IllegalAccessException, InstantiationException {

                Object empresaParceiraCriacaoCnpjNulo = EmpresaParceiraMockFactory.getInstance(
                        null,
                        "Empresa Engraçada, S.A.",
                        null,
                        "Rua dos bobos, 0"
                );

                HttpEntity<EmpresaParceira> requestEntity = new HttpEntity<>((EmpresaParceira) empresaParceiraCriacaoCnpjNulo);
                ResponseEntity<String> resposta = restTemplate.exchange(
                        "/empresas/{id}",
                        HttpMethod.PUT,
                        requestEntity,
                        String.class,
                        1
                );

                assertEquals(HttpStatus.BAD_REQUEST, resposta.getStatusCode());
            }
        }

        @Nested
        @DisplayName("4.2 cenário: update quando empresa não existe deve retornar empresa e código 404 NOT FOUND")
        @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
        public class EmpresaParceiraControllerIntegrationTestEmpresaNaoExiste404 {

            @Autowired
            private TestRestTemplate restTemplate;

            @Test
            @DisplayName("Cenário único")
            public void testGetEmpresaParceiraNotFound() throws NoSuchFieldException, InvocationTargetException, IllegalAccessException, InstantiationException {

                Object empresaParceiraCriacao = EmpresaParceiraMockFactory.getInstance(
                        null,
                        "Empresa Engraçada, S.A.",
                        "27.096.560/0001-48",
                        "Rua dos bobos, 0"
                );


                HttpEntity<EmpresaParceira> requestEntity = new HttpEntity<>((EmpresaParceira) empresaParceiraCriacao);
                ResponseEntity<String> resposta = restTemplate.exchange(
                        "/empresas/{id}",
                        HttpMethod.PUT,
                        requestEntity,
                        String.class,
                        999
                );

                assertEquals(HttpStatus.NOT_FOUND, resposta.getStatusCode());
            }
        }

        @Test
        @DisplayName("4.1 cenário: update quando empresa existe deve retornar empresa e código 200 OK")
        void update_quandoEmpresaExiste_deveRetornarEmpresa() throws NoSuchFieldException, InvocationTargetException, IllegalAccessException, InstantiationException {


            Object empresaParceiraAtualizacao = EmpresaParceiraMockFactory.getInstance(
                    1,
                    "Empresa Engraçada, S.A.",
                    "27.096.560/0001-48",
                    "Rua dos bobos, 0"
            );

            Object empresaParceiraBuscaRetorno = EmpresaParceiraMockFactory.getInstance(
                    1,
                    "Empresa Engraçada, S.A.",
                    "27.096.560/0001-48",
                    "Rua dos bobos, 0"
            );

            // when
            when(empresaParceiraService.update(1, (EmpresaParceira) empresaParceiraAtualizacao)).thenReturn((EmpresaParceira) empresaParceiraBuscaRetorno);

            // then
            ResponseEntity<EmpresaParceira> response = empresaParceiraController.update(1, (EmpresaParceira) empresaParceiraAtualizacao);

            // assert
            assertEquals(200, response.getStatusCodeValue());
            assertTrue(Objects.nonNull(response.getBody()));
        }
    }
}