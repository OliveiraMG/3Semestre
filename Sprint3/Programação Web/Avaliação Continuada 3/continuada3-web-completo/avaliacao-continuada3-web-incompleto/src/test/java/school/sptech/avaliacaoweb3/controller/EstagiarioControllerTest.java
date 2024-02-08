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
import org.springframework.http.ResponseEntity;
import school.sptech.avaliacaoweb3.entity.Estagiario;
import school.sptech.avaliacaoweb3.factory.EmpresaParceiraMockFactory;
import school.sptech.avaliacaoweb3.factory.EstagiarioMockFactory;
import school.sptech.avaliacaoweb3.service.EstagiarioService;

import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Testes para o controller: EstagiarioController")
class EstagiarioControllerTest {

    @Mock
    private EstagiarioService estagiarioService;

    @InjectMocks
    private EstagiarioController estagiarioController;

    @Nested
    @DisplayName("1. Testes para o método findAll") //classe
    class TestesParaMetodoFindAll {

        @Test
        @DisplayName("1.2 Deve retornar 204 quando não houver estagiários cadastrados")
        void deveRetornarUmaListaVazia() throws NoSuchFieldException, InvocationTargetException, IllegalAccessException, InstantiationException {

            // given

            Object emp1 = EmpresaParceiraMockFactory.getInstance(1, "PTM Souza", "56.457.672/0001-85", "Rua dos Bobos, 0");
            Object emp2 = EmpresaParceiraMockFactory.getInstance(2, "PTM Silas", "04.707.486/0001-53", "Rua dos Bobos, 0");

            Object estag1 = EstagiarioMockFactory.getInstance(1, "João", "12345678901", emp1);
            Object estag2 = EstagiarioMockFactory.getInstance(2, "Maria", "12345678902", emp2);

            List<Object> estagiariosRfl = List.of(estag1, estag2);

            List<Estagiario> estagiarios = estagiariosRfl.stream()
                    .map(estag -> (Estagiario) estag)
                    .toList();

            // when

            when(estagiarioService.findAll()).thenReturn(estagiarios);

            // then
            ResponseEntity<List<Estagiario>> resposta = estagiarioController.findAll();

            //assert
            assertEquals(200, resposta.getStatusCodeValue());
            assertFalse(resposta.getBody().isEmpty());
        }

        @Test
        @DisplayName("1.1. Deve retornar uma lista de estagiários")
        void deveRetornarUmaListaDeEstagiarios() {
            // when
            when(estagiarioService.findAll()).thenReturn(Collections.emptyList());

            // then
            ResponseEntity<List<Estagiario>> resposta = estagiarioController.findAll();

            //assert
            assertEquals(204, resposta.getStatusCodeValue());
            assertNull(resposta.getBody());
        }
    }

    @Nested
    @DisplayName("2. Testes para o método findAllByEmpresaNome")//classe
    class TestesParaMetodoFindAllByEmpresaNome {

        @Nested
        @DisplayName("2.2. Testes para o método findAllByEmpresaNome cenário corretos")//classe
        @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
        public class TestesParaMetodoFindAllByEmpresaNomeCenarioCorreto {

            @Autowired
            private TestRestTemplate restTemplate;

            @Test
            @DisplayName("Cenário: Deve retornar uma lista de estagiários filtrada")
            void deveRetornarUmaListaDeEstagiariosFiltrada() {

                // then
                ResponseEntity<List> forEntity = restTemplate.getForEntity("/estagiarios/empresa?nome=Empresa Alpha", List.class);

                //assert
                assertEquals(200, forEntity.getStatusCodeValue());
                assertEquals(3, forEntity.getBody().size());
            }

            @Test
            @DisplayName("Cenário: Deve retornar uma lista de estagiários filtrada")
            void deveRetornarUmaListaDeEstagiariosFiltrada2() {

                // then
                ResponseEntity<List> forEntity = restTemplate.getForEntity("/estagiarios/empresa?nome=Empresa Beta", List.class);

                //assert
                assertEquals(200, forEntity.getStatusCodeValue());
                assertEquals(2, forEntity.getBody().size());
            }
        }

        @Test
        @DisplayName("2.1 Deve retornar 204 quando não houver estagiários cadastrados com o nome da empresa")
        void deveRetornarUmaListaVazia() {

            //when
            when(estagiarioService.findAllByEmpresaParceiraNome("Empresa Alpha")).thenReturn(Collections.emptyList());

            // then
            ResponseEntity<List<Estagiario>> resposta = estagiarioController.findAllByEmpresaNome("Empresa Alpha");

            //assert
            assertEquals(204, resposta.getStatusCodeValue());
            assertNull(resposta.getBody());
        }
    }

    @Nested
    @DisplayName("3. Testes para o método findById")//classe
    class TestesParaMetodoFindById {

        @Test
        @DisplayName("3.1 Deve retornar um estagiário com o id informado")
        void deveRetornarUmEstagiarioComOIdInformado() throws NoSuchFieldException, InvocationTargetException, IllegalAccessException, InstantiationException {

            // given
            Object emp1 = EmpresaParceiraMockFactory.getInstance(1, "PTM Souza", "56.457.672/0001-85", "Rua dos Bobos, 0");
            Object estag1 = EstagiarioMockFactory.getInstance(1, "João", "12345678901", emp1);

            // when
            when(estagiarioService.findById(1)).thenReturn((Estagiario) estag1);

            // then
            ResponseEntity<Estagiario> resposta = estagiarioController.findById(1);

            //assert
            assertEquals(200, resposta.getStatusCodeValue());
            assertNotNull(resposta.getBody());
        }

        @Nested
        @DisplayName("3.2 Testes para o método findById cenário incorreto") //classe
        @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
        class TestesParaMetodoFindByIdCenarioIncorreto {

            @Autowired
            private TestRestTemplate restTemplate;

            @Test
            @DisplayName("Cenário: Deve retornar 404 quando não encontrar um estagiário com o id informado")
            void deveRetornar404QuandoNaoEncontrarUmEstagiarioComOIdInformado() {

                // then
                ResponseEntity resposta = restTemplate.getForEntity("/estagiarios/{id}", String.class, 999);

                //assert
                assertEquals(404, resposta.getStatusCodeValue());
            }
        }
    }

    @Nested
    @DisplayName("4. Testes para o método save")//classe
    class TestesParaMetodoSave {

        @Nested
        @DisplayName("4.2 Testes para o método save cenário correto")//classe
        @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
        class TestesParaMetodoSaveCenarioCorreto {

            @Autowired
            private TestRestTemplate restTemplate;

            @Test
            @DisplayName("Cenário: Deve salvar um estagiário")
            void deveSalvarUmEstagiario() throws NoSuchFieldException, InvocationTargetException, IllegalAccessException, InstantiationException {

                // given
                Object emp1 = EmpresaParceiraMockFactory.getInstance(1, "PTM Souza", "56.654.606/0001-03", "Rua dos Bobos, 0");
                Object estag1 = EstagiarioMockFactory.getInstance(1, "João", "584.691.190-01", emp1);

                // then
                ResponseEntity<Estagiario> resposta = restTemplate.postForEntity("/estagiarios", estag1, Estagiario.class);

                //assert
                assertEquals(200, resposta.getStatusCodeValue());
                assertNotNull(resposta.getBody());
            }
        }

        @Nested
        @DisplayName("4.1 Testes para o método save cenário incorreto")//classe
        @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
        class TestesParaMetodoSaveCenarioIncorreto {

            @Autowired
            private TestRestTemplate restTemplate;

            @Nested
            @DisplayName("4.1.2 Testes para o método save cenário incorreto - nome")//classe
            class TestesParaMetodoSaveCenarioIncorretoNome {

                @Test
                @DisplayName("Cenário: Deve retornar 400 quando o nome do estagiário for nulo")
                void deveRetornar400QuandoONomeDoEstagiarioForNulo() throws NoSuchFieldException, InvocationTargetException, IllegalAccessException, InstantiationException {

                    // given
                    Object emp1 = EmpresaParceiraMockFactory.getInstance(1, "PTM Souza", "56.654.606/0001-03", "Rua dos Bobos, 0");
                    Object estag1 = EstagiarioMockFactory.getInstance(1, null, "584.691.190-01", emp1);

                    // then
                    ResponseEntity<Estagiario> resposta = restTemplate.postForEntity("/estagiarios", estag1, Estagiario.class);

                    //assert
                    assertEquals(400, resposta.getStatusCodeValue());
                }

                @Test
                @DisplayName("Cenário: Deve retornar 400 quando o nome do estagiário for vazio")
                void deveRetornar400QuandoONomeDoEstagiarioForVazio() throws NoSuchFieldException, InvocationTargetException, IllegalAccessException, InstantiationException {

                    // given
                    Object emp1 = EmpresaParceiraMockFactory.getInstance(1, "PTM Souza", "56.654.606/0001-03", "Rua dos Bobos, 0");
                    Object estag1 = EstagiarioMockFactory.getInstance(1, "", "584.691.190-01", emp1);

                    // then
                    ResponseEntity<Estagiario> resposta = restTemplate.postForEntity("/estagiarios", estag1, Estagiario.class);

                    //assert
                    assertEquals(400, resposta.getStatusCodeValue());
                }

                @Test
                @DisplayName("Cenário: Deve retornar 400 quando o nome do estagiário branco")
                void deveRetornar400QuandoONomeDoEstagiarioForMaiorQue255Caracteres() throws NoSuchFieldException, InvocationTargetException, IllegalAccessException, InstantiationException {

                    // given
                    Object emp1 = EmpresaParceiraMockFactory.getInstance(1, "PTM Souza", "56.654.606/0001-03", "Rua dos Bobos, 0");
                    Object estag = EstagiarioMockFactory.getInstance(1, " ", "584.691.190-01", emp1);

                    // then
                    ResponseEntity<Estagiario> resposta = restTemplate.postForEntity("/estagiarios", estag, Estagiario.class);

                    //assert
                    assertEquals(400, resposta.getStatusCodeValue());
                }
            }

            @Nested
            @DisplayName("4.1.3 Testes para o método save cenário incorreto - cpf")//classe
            class TestesParaMetodoSaveCenarioIncorretoCpf {

                @Test
                @DisplayName("Cenário: Deve retornar 400 quando o cpf do estagiário for nulo")
                void deveRetornar400QuandoOCpfDoEstagiarioForNulo() throws NoSuchFieldException, InvocationTargetException, IllegalAccessException, InstantiationException {

                    // given
                    Object emp1 = EmpresaParceiraMockFactory.getInstance(1, "PTM Souza", "56.654.606/0001-03", "Rua dos Bobos, 0");
                    Object estag1 = EstagiarioMockFactory.getInstance(1, "João", null, emp1);

                    // then
                    ResponseEntity<Estagiario> resposta = restTemplate.postForEntity("/estagiarios", estag1, Estagiario.class);

                    //assert
                    assertEquals(400, resposta.getStatusCodeValue());
                }

                @Test
                @DisplayName("Cenário: Deve retornar 400 quando o cpf do estagiário for vazio")
                void deveRetornar400QuandoOCpfDoEstagiarioForVazio() throws NoSuchFieldException, InvocationTargetException, IllegalAccessException, InstantiationException {

                    // given
                    Object emp1 = EmpresaParceiraMockFactory.getInstance(1, "PTM Souza", "56.654.606/0001-03", "Rua dos Bobos, 0");
                    Object estag1 = EstagiarioMockFactory.getInstance(1, "João", "", emp1);

                    // then
                    ResponseEntity<Estagiario> resposta = restTemplate.postForEntity("/estagiarios", estag1, Estagiario.class);

                    //assert
                    assertEquals(400, resposta.getStatusCodeValue());
                }

                @Test
                @DisplayName("Cenário: Deve retornar 400 quando o cpf do estagiário for branco")
                void deveRetornar400QuandoOCpfDoEstagiarioForBranco() throws NoSuchFieldException, InvocationTargetException, IllegalAccessException, InstantiationException {

                    // given
                    Object emp1 = EmpresaParceiraMockFactory.getInstance(1, "PTM Souza", "56.654.606/0001-03", "Rua dos Bobos, 0");
                    Object estag1 = EstagiarioMockFactory.getInstance(1, "João", " ", emp1);

                    // then
                    ResponseEntity<Estagiario> resposta = restTemplate.postForEntity("/estagiarios", estag1, Estagiario.class);

                    // assert
                    assertEquals(400, resposta.getStatusCodeValue());
                }

                @Test
                @DisplayName("Cenário: Deve retornar 400 quando o cpf do estagiário for inválido")
                void deveRetornar400QuandoOCpfDoEstagiarioForInvalido() throws NoSuchFieldException, InvocationTargetException, IllegalAccessException, InstantiationException {

                    // given
                    Object estag1 = EstagiarioMockFactory.getInstance(1, "João", "584.691.190-00", null);

                    // then
                    ResponseEntity<Estagiario> resposta = restTemplate.postForEntity("/estagiarios", estag1, Estagiario.class);

                    // assert
                    assertEquals(400, resposta.getStatusCodeValue());
                }

                @Test
                @DisplayName("Cenário: Deve retornar 409 quando o CPF já estiver cadastrado")
                void deveRetornar409QuandoCpfInformadaJaEstiverCadastrado() throws NoSuchFieldException, InvocationTargetException, IllegalAccessException, InstantiationException {

                    //given
                    Object estag1 = EstagiarioMockFactory.getInstance(1, "João", "36211398000", null);

                    //then
                    ResponseEntity<Estagiario> resposta = restTemplate.postForEntity("/estagiarios", estag1, Estagiario.class);

                    //assert
                    assertEquals(409, resposta.getStatusCodeValue());
                }

            }
        }
    }

    @Nested
    @DisplayName("5. Testes para o método vincularEmpresaParceira")//classe
    class TestesParaMetodoUpdate {

        @Nested
        @DisplayName("5.2 Testes para o método vincularEmpresaParceira cenário correto")//classe
        @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
        class TestesParaMetodoVincularEmpresaParceiraCenarioCorreto {

            @Autowired
            private TestRestTemplate restTemplate;

            @Test
            @DisplayName("Cenário: Deve vincular uma empresa parceira a um estagiário")
            void deveVincularUmaEmpresaParceiraAUmEstagiario() throws NoSuchFieldException, InvocationTargetException, IllegalAccessException, InstantiationException {

                //given
                final int idEstagiario = 15;
                final int idEmpresaParceira = 11;

                Object empresa = EmpresaParceiraMockFactory.getInstance(idEmpresaParceira, "Yoshi Industries", "37.224.652/0001-10", "Vila dos Atletas, 333");
                Estagiario estag = (Estagiario) EstagiarioMockFactory.getInstance(idEstagiario, "José", "24463903076", empresa);

                // when
                HttpEntity<Estagiario> requestEntity = new HttpEntity<>(estag);

                // then
                ResponseEntity<String> resposta = restTemplate.exchange(
                        "/estagiarios/{idEstagiario}/vinculo/{idEmpresaParceira}",
                        HttpMethod.PUT,
                        requestEntity,
                        String.class,
                        idEstagiario,
                        idEmpresaParceira
                );

                //assert
                assertEquals(200, resposta.getStatusCodeValue());
                assertNotNull(resposta.getBody());
            }
        }

        @Nested
        @DisplayName("5.1 Testes para o método vincularEmpresaParceira cenário incorreto")//classe
        @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
        class TestesParaMetodoVincularEmpresaParceiraCenarioIncorreto {

            @Autowired
            private TestRestTemplate restTemplate;

            @Nested
            @DisplayName("5.1.2 Testes para o método vincularEmpresaParceira cenário incorreto - idEstagiario")//classe
            class TestesParaMetodoVincularEmpresaParceiraCenarioIncorretoIdEstagiario {

                @Test
                @DisplayName("Cenário: Deve retornar 404 quando o id do estagiário for 0")
                void deveRetornar400QuandoOIdDoEstagiarioFor0() throws NoSuchFieldException, InvocationTargetException, IllegalAccessException, InstantiationException {

                    // given
                    final int idEstagiario = 0;
                    final int idEmpresaParceira = 11;

                    Object empresa = EmpresaParceiraMockFactory.getInstance(idEmpresaParceira, "Yoshi Industries", "37.224.652/0001-10", "Vila dos Atletas, 333");
                    Estagiario estag = (Estagiario) EstagiarioMockFactory.getInstance(idEstagiario, "José", "24463903076", empresa);

                    // when
                    HttpEntity<Estagiario> requestEntity = new HttpEntity<>(estag);

                    // then
                    ResponseEntity<String> resposta = restTemplate.exchange(
                            "/estagiarios/{idEstagiario}/vinculo/{idEmpresaParceira}",
                            HttpMethod.PUT,
                            requestEntity,
                            String.class,
                            idEstagiario,
                            idEmpresaParceira
                    );

                    //assert
                    assertEquals(404, resposta.getStatusCodeValue());
                }

                @Test
                @DisplayName("Cenário: Deve retornar 404 quando o id do estagiário não for encontrado")
                void deveRetornar400QuandoOIdDoEstagiarioFor99999() throws NoSuchFieldException, InvocationTargetException, IllegalAccessException, InstantiationException {

                    // given
                    final int idEstagiario = 99999;
                    final int idEmpresaParceira = 11;

                    Object empresa = EmpresaParceiraMockFactory.getInstance(idEmpresaParceira, "Yoshi Industries", "37.224.652/0001-10", "Vila dos Atletas, 333");
                    Estagiario estag = (Estagiario) EstagiarioMockFactory.getInstance(idEstagiario, "José", "24463903076", empresa);

                    // when
                    HttpEntity<Estagiario> requestEntity = new HttpEntity<>(estag);

                    // then
                    ResponseEntity<String> resposta = restTemplate.exchange(
                            "/estagiarios/{idEstagiario}/vinculo/{idEmpresaParceira}",
                            HttpMethod.PUT,
                            requestEntity,
                            String.class,
                            idEstagiario,
                            idEmpresaParceira
                    );

                    //assert
                    assertEquals(404, resposta.getStatusCodeValue());
                }

                @Test
                @DisplayName("Cenário: Deve retornar 404 quando o id do estagiário for negativo")
                void deveRetornar400QuandoOIdDoEstagiarioForNegativo() throws NoSuchFieldException, InvocationTargetException, IllegalAccessException, InstantiationException {

                    // given
                    final int idEstagiario = -1;
                    final int idEmpresaParceira = 11;

                    Object empresa = EmpresaParceiraMockFactory.getInstance(idEmpresaParceira, "Yoshi Industries", "37.224.652/0001-10", "Vila dos Atletas, 333");
                    Estagiario estag = (Estagiario) EstagiarioMockFactory.getInstance(idEstagiario, "José", "24463903076", empresa);

                    // when
                    HttpEntity<Estagiario> requestEntity = new HttpEntity<>(estag);

                    // then
                    ResponseEntity<String> resposta = restTemplate.exchange(
                            "/estagiarios/{idEstagiario}/vinculo/{idEmpresaParceira}",
                            HttpMethod.PUT,
                            requestEntity,
                            String.class,
                            idEstagiario,
                            idEmpresaParceira
                    );

                    //assert
                    assertEquals(404, resposta.getStatusCodeValue());
                }
            }

            @Nested
            @DisplayName("5.1.3 Testes para o método vincularEmpresaParceira cenário incorreto - idEmpresaParceira")//classe
            class TestesParaMetodoVincularEmpresaParceiraCenarioIncorretoIdEmpresaParceira {

                @Test
                @DisplayName("Cenário: Deve retornar 404 quando o id da empresa parceira for 0")
                void deveRetornar400QuandoOIdDaEmpresaParceiraFor0() throws NoSuchFieldException, InvocationTargetException, IllegalAccessException, InstantiationException {

                    // given
                    final int idEstagiario = 15;
                    final int idEmpresaParceira = 0;

                    Object empresa = EmpresaParceiraMockFactory.getInstance(idEmpresaParceira, "Yoshi Industries", "37.224.652/0001-10", "Vila dos Atletas, 333");
                    Estagiario estag = (Estagiario) EstagiarioMockFactory.getInstance(idEstagiario, "José", "24463903076", empresa);

                    // when
                    HttpEntity<Estagiario> requestEntity = new HttpEntity<>(estag);

                    // then
                    ResponseEntity<String> resposta = restTemplate.exchange(
                            "/estagiarios/{idEstagiario}/vinculo/{idEmpresaParceira}",
                            HttpMethod.PUT,
                            requestEntity,
                            String.class,
                            idEstagiario,
                            idEmpresaParceira
                    );

                    //assert
                    assertEquals(404, resposta.getStatusCodeValue());
                }

                @Test
                @DisplayName("Cenário: Deve retornar 404 quando o id da empresa parceira for negativo")
                void deveRetornar400QuandoOIdDaEmpresaParceiraForNegativo() throws NoSuchFieldException, InvocationTargetException, IllegalAccessException, InstantiationException {

                    // given
                    final int idEstagiario = 15;
                    final int idEmpresaParceira = -1;

                    Object empresa = EmpresaParceiraMockFactory.getInstance(idEmpresaParceira, "Yoshi Industries", "37.224.652/0001-10", "Vila dos Atletas, 333");
                    Estagiario estag = (Estagiario) EstagiarioMockFactory.getInstance(idEstagiario, "José", "24463903076", empresa);

                    // when
                    HttpEntity<Estagiario> requestEntity = new HttpEntity<>(estag);

                    // then
                    ResponseEntity<String> resposta = restTemplate.exchange(
                            "/estagiarios/{idEstagiario}/vinculo/{idEmpresaParceira}",
                            HttpMethod.PUT,
                            requestEntity,
                            String.class,
                            idEstagiario,
                            idEmpresaParceira
                    );

                    //assert
                    assertEquals(404, resposta.getStatusCodeValue());
                }


                @Test
                @DisplayName("Cenário: Deve retornar 404 quando o id da empresa parceira não for encontrado")
                void deveRetornar400QuandoOIdDaEmpresaParceiraFor99999() throws NoSuchFieldException, InvocationTargetException, IllegalAccessException, InstantiationException {

                    // given
                    final int idEstagiario = 15;
                    final int idEmpresaParceira = 99999;

                    Object empresa = EmpresaParceiraMockFactory.getInstance(idEmpresaParceira, "Yoshi Industries", "37.224.652/0001-10", "Vila dos Atletas, 333");
                    Estagiario estag = (Estagiario) EstagiarioMockFactory.getInstance(idEstagiario, "José", "24463903076", empresa);

                    // when
                    HttpEntity<Estagiario> requestEntity = new HttpEntity<>(estag);

                    // then
                    ResponseEntity<String> resposta = restTemplate.exchange(
                            "/estagiarios/{idEstagiario}/vinculo/{idEmpresaParceira}",
                            HttpMethod.PUT,
                            requestEntity,
                            String.class,
                            idEstagiario,
                            idEmpresaParceira
                    );

                    //assert
                    assertEquals(404, resposta.getStatusCodeValue());
                }
            }
        }
    }
}