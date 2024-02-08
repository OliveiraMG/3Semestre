package school.sptech.avaliacaoweb3.factory;

import school.sptech.avaliacaoweb3.entity.EmpresaParceira;
import school.sptech.avaliacaoweb3.utils.EmpresaParceiraUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class EmpresaParceiraMockFactory {

    public static Object getInstance(Object id, Object nome, Object cnpj, Object endereco) throws NoSuchFieldException, IllegalAccessException, InvocationTargetException, InstantiationException {

        Class<EmpresaParceira> clazz = EmpresaParceira.class;

        Constructor<?> constructor = clazz.getConstructors()[0];

        Object empresaParceira = constructor.newInstance();

        Field campoId = clazz.getDeclaredField(EmpresaParceiraUtils.NOME_CAMPO_ID);
        Field campoNome = clazz.getDeclaredField(EmpresaParceiraUtils.NOME_CAMPO_NOME);
        Field campoCnpj = clazz.getDeclaredField(EmpresaParceiraUtils.NOME_CAMPO_CNPJ);
        Field campoEndereco = clazz.getDeclaredField(EmpresaParceiraUtils.NOME_CAMPO_ENDERECO);


        campoId.setAccessible(true);
        campoNome.setAccessible(true);
        campoCnpj.setAccessible(true);
        campoEndereco.setAccessible(true);

        campoId.set(empresaParceira, id);
        campoNome.set(empresaParceira, nome);
        campoCnpj.set(empresaParceira, cnpj);
        campoEndereco.set(empresaParceira, endereco);

        return empresaParceira;
    }
}
