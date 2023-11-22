package school.sptech.atividade1.factory;

import school.sptech.atividade1.controller.dto.UsuarioSimpleResponse;
import school.sptech.atividade1.utils.UsuarioSimpleResponseDtoUtil;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class UsuarioSimpleResponseDtoFactory {

    public static Object getInstance(Object id, Object nomeCompleto)
            throws NoSuchFieldException, IllegalAccessException, InvocationTargetException, InstantiationException {

        Class<UsuarioSimpleResponse> clazz = UsuarioSimpleResponse.class;

        Constructor<?> constructor = clazz.getConstructors()[0];

        Object[] args = new Object[constructor.getParameterCount()];

        Object usuario = constructor.newInstance(args);

        Field idField = clazz
                .getDeclaredField(UsuarioSimpleResponseDtoUtil.USUARIO_SIMPLE_RESPONSE_DTO_ID);
        Field nomeCompletoField = clazz
                .getDeclaredField(UsuarioSimpleResponseDtoUtil.USUARIO_SIMPLE_RESPONSE_DTO_NOME_COMPLETO);

        idField.setAccessible(true);
        nomeCompletoField.setAccessible(true);

        return usuario;
    }
}
