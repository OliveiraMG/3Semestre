package school.sptech.avaliacaoweb3.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import school.sptech.avaliacaoweb3.utils.EmpresaParceiraUtils;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Teste da entidade: EmpresaParceira")
class EmpresaParceiraTest {

    @Nested
    @DisplayName("3. Teste de fidelidade a especificação")
    public class TesteDeFidelidadeAEspecificacao {

        @Test
        @DisplayName("Os atributos devem seguir a especificação de nomenclatura")
        void atributosDevemSeguirEspecificacaoDeNomenclatura() {

            Class<EmpresaParceira> clazz = EmpresaParceira.class;

            for (String campo : EmpresaParceiraUtils.campos) {
                assertDoesNotThrow(() -> clazz.getDeclaredField(campo));
            }
        }
    }

    @Nested
    @DisplayName("2. Teste de encapsulamento")
    public class TesteDeEncapsulamento {


        @Test
        @DisplayName("Cenário: Os atributos devem privados")
        void atributosDevemSeguirEspecificacaoDeNomenclatura() {

            Class<EmpresaParceira> clazz = EmpresaParceira.class;

            Field[] declaredFields = clazz.getDeclaredFields();

            assertFalse(declaredFields.length == 0);

            for (Field declaredField : declaredFields) {
                int modifiers = declaredField.getModifiers();

                assertTrue((modifiers & 2) == 2);
            }
        }

        @Test
        @DisplayName("Cenário: Os atributos devem possui métodos de acesso")
        void atributosDevemPossuiMetodosDeAcesso() {

            Class<EmpresaParceira> clazz = EmpresaParceira.class;

            Field[] declaredFields = clazz.getDeclaredFields();

            assertFalse(declaredFields.length == 0);

            for (Field declaredField : declaredFields) {
                String nomeCampo = declaredField.getName();

                String nomeMetodoGet = "get" + nomeCampo.substring(0, 1).toUpperCase() + nomeCampo.substring(1);
                String nomeMetodoSet = "set" + nomeCampo.substring(0, 1).toUpperCase() + nomeCampo.substring(1);

                assertDoesNotThrow(() -> clazz.getDeclaredMethod(nomeMetodoGet));
                assertDoesNotThrow(() -> clazz.getDeclaredMethod(nomeMetodoSet, declaredField.getType()));
            }
        }
    }

    @Nested
    @DisplayName("1. Teste de Mapeamento (Anotações)")
    public class TesteDeMapeamento {

        @Test
        @DisplayName("Cenário: A classe deve possuir a anotação @Entity")
        void classeDevePossuirAnotacaoEntity() {
            Entity declaredAnnotation = EmpresaParceira.class.getDeclaredAnnotation(Entity.class);
            assertNotNull(declaredAnnotation, "A classe deve possuir a anotação @Entity");
        }

        @Test
        @DisplayName("Cenário: A classe deve possuir a anotação @Id")
        void classeDevePossuirAnotacaoId() throws NoSuchFieldException {
            Id declaredAnnotation = EmpresaParceira.class.getDeclaredField(EmpresaParceiraUtils.NOME_CAMPO_ID).getDeclaredAnnotation(Id.class);
            assertNotNull(declaredAnnotation, "A classe deve possuir a anotação @Id");
        }

        @Test
        @DisplayName("Cenário: A classe deve possuir a anotação @GeneratedValue")
        void classeDevePossuirAnotacaoGeneratedValue() throws NoSuchFieldException {
            GeneratedValue declaredAnnotation = EmpresaParceira.class.getDeclaredField(EmpresaParceiraUtils.NOME_CAMPO_ID).getDeclaredAnnotation(GeneratedValue.class);
            assertNotNull(declaredAnnotation, "A classe deve possuir a anotação @GeneratedValue");
        }
    }
}