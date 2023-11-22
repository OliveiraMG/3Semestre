package school.sptech.atividade1.factory;

import school.sptech.atividade1.controller.dto.UsuarioCreateRequestDto;
import school.sptech.atividade1.utils.UsuarioCreateRequestDtoUtil;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class UsuarioCreateRequestDtoFactory {

    public static Object getInstance(Object nome, Object sobrenome, Object cpf, Object email, Object dataNascimento)
            throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException {

        Class<UsuarioCreateRequestDto> clazz = UsuarioCreateRequestDto.class;

        Constructor<?> constructor = clazz.getConstructors()[0];

        Object[] args = new Object[constructor.getParameterCount()];

        Object usuario = constructor.newInstance(args);

        Field nomeField = clazz
                .getDeclaredField(UsuarioCreateRequestDtoUtil.USUARIO_CREATE_REQUEST_DTO_NOME);
        Field sobrenomeField = clazz
                .getDeclaredField(UsuarioCreateRequestDtoUtil.USUARIO_CREATE_REQUEST_DTO_SOBRENOME);
        Field cpfField = clazz
                .getDeclaredField(UsuarioCreateRequestDtoUtil.USUARIO_CREATE_REQUEST_DTO_CPF);
        Field emailField = clazz
                .getDeclaredField(UsuarioCreateRequestDtoUtil.USUARIO_CREATE_REQUEST_DTO_EMAIL);
        Field dataNascimentoField = clazz
                .getDeclaredField(UsuarioCreateRequestDtoUtil.USUARIO_CREATE_REQUEST_DTO_DATA_NASCIMENTO);

        nomeField.setAccessible(true);
        sobrenomeField.setAccessible(true);
        cpfField.setAccessible(true);
        emailField.setAccessible(true);
        dataNascimentoField.setAccessible(true);

        nomeField.set(usuario, nome);
        sobrenomeField.set(usuario, sobrenome);
        cpfField.set(usuario, cpf);
        emailField.set(usuario, email);
        dataNascimentoField.set(usuario, dataNascimento);

        return usuario;
    }
}
