package school.sptech.avaliacaoweb3.factory;

import school.sptech.avaliacaoweb3.entity.Estagiario;
import school.sptech.avaliacaoweb3.utils.EstagiarioUtil;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class EstagiarioMockFactory {

    public static Object getInstance(Object id, Object nome, Object cpf, Object empresaParceira) throws NoSuchFieldException, IllegalAccessException, InvocationTargetException, InstantiationException {

        Class<Estagiario> clazz = Estagiario.class;

        Constructor<?> constructor = clazz.getConstructors()[0];

        Object estagiario = constructor.newInstance();

        Field campoId = clazz.getDeclaredField(EstagiarioUtil.NOME_CAMPO_ID);
        Field campoNome = clazz.getDeclaredField(EstagiarioUtil.NOME_CAMPO_NOME);
        Field campoCpf = clazz.getDeclaredField(EstagiarioUtil.NOME_CAMPO_CPF);
        Field campoEmpresaParceira = clazz.getDeclaredField(EstagiarioUtil.NOME_CAMPO_EMPRESA_PARCEIRA);

        campoId.setAccessible(true);
        campoNome.setAccessible(true);
        campoCpf.setAccessible(true);
        campoEmpresaParceira.setAccessible(true);

        campoId.set(estagiario, id);
        campoNome.set(estagiario, nome);
        campoCpf.set(estagiario, cpf);
        campoEmpresaParceira.set(estagiario, empresaParceira);

        return estagiario;
    }
}
