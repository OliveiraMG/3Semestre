package school.sptech.avaliacaoweb3.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import school.sptech.avaliacaoweb3.utils.EstagiarioUtil;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Teste da entidade: Estagiario")
class EstagiarioTest {

    @Nested
    @DisplayName("3. Teste de fidelidade a especificação")
    public class TesteDeFidelidadeAEspecificacao {

        @Test
        @DisplayName("Os atributos devem seguir a especificação de nomenclatura")
        void atributosDevemSeguirEspecificacaoDeNomenclatura() {

            Class<Estagiario> clazz = Estagiario.class;

            for (String campo : EstagiarioUtil.CAMPOS) {
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
        @DisplayName("Cenário: Os atributos devem possuir metodos de acesso")
        void atributosDevemPossuirMetodosDeAcesso() {

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
    public class TesteDeExistencia {

        @Test
        @DisplayName("Cenário: A classe deve possuir a anotação @Entity")
        void classeDevePossuirAnotacaoEntity() {
            Entity annotation = Estagiario.class.getAnnotation(Entity.class);
            assertNotNull(annotation, "A classe deve possuir a anotação @Entity");
        }

        @Test
        @DisplayName("Cenário: A classe deve possuir a anotação @Id")
        void classeDevePossuirAnotacaoId() throws NoSuchFieldException {
            Field id = Estagiario.class.getDeclaredField("id");
            Id annotation = id.getAnnotation(jakarta.persistence.Id.class);
            assertNotNull(annotation, "A classe deve possuir a anotação @Id");
        }

        @Test
        @DisplayName("Cenário: A classe deve possuir a anotação @GeneratedValue")
        void classeDevePossuirAnotacaoGeneratedValue() throws NoSuchFieldException {
            Field id = Estagiario.class.getDeclaredField("id");
            GeneratedValue annotation = id.getAnnotation(jakarta.persistence.GeneratedValue.class);
            assertNotNull(annotation, "A classe deve possuir a anotação @GeneratedValue");
        }

        @Test
        @DisplayName("Cenário: A classe deve possuir a anotação de relacionamento")
        void classeDevePossuirAnotacaoManyToOne() throws NoSuchFieldException {
            Field id = Estagiario.class.getDeclaredField(EstagiarioUtil.NOME_CAMPO_EMPRESA_PARCEIRA);
            ManyToOne annotation = id.getAnnotation(jakarta.persistence.ManyToOne.class);
            assertNotNull(annotation, "Está faltando a anotação de relacionamento");
        }
    }
}