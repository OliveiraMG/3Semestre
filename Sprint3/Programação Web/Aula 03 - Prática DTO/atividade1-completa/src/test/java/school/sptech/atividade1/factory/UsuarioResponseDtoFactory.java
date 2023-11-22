package school.sptech.atividade1.factory;

import school.sptech.atividade1.controller.dto.UsuarioResponseDto;
import school.sptech.atividade1.utils.UsuarioResponseDtoUtil;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class UsuarioResponseDtoFactory {

    public static Object getInstance(Object id, Object nomeCompleto, Object documento, Object dataNascimento, Object contato)
            throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException {

        Class<UsuarioResponseDto> clazz = UsuarioResponseDto.class;

        Constructor<?> constructor = clazz.getConstructors()[0];

        Object[] args = new Object[constructor.getParameterCount()];

        Object usuario = constructor.newInstance(args);

        Field idField = clazz.getDeclaredField(UsuarioResponseDtoUtil.USUARIO_RESPONSE_DTO_ID);
        Field nomeCompletoField = clazz.getDeclaredField(UsuarioResponseDtoUtil.USUARIO_RESPONSE_DTO_NOME_COMPLETO);
        Field documentoField = clazz.getDeclaredField(UsuarioResponseDtoUtil.USUARIO_RESPONSE_DTO_DOCUMENTO);
        Field dataNascimentoField = clazz.getDeclaredField(UsuarioResponseDtoUtil.USUARIO_RESPONSE_DTO_DATA_NASCIMENTO);
        Field contatoField = clazz.getDeclaredField(UsuarioResponseDtoUtil.USUARIO_RESPONSE_DTO_CONTATO);

        idField.setAccessible(true);
        nomeCompletoField.setAccessible(true);
        documentoField.setAccessible(true);
        dataNascimentoField.setAccessible(true);
        contatoField.setAccessible(true);

        idField.set(usuario, id);
        nomeCompletoField.set(usuario, nomeCompleto);
        documentoField.set(usuario, documento);
        dataNascimentoField.set(usuario, dataNascimento);
        contatoField.set(usuario, contato);

        return usuario;
    }

    public static Object getInstance(Map<String, Object> args)
            throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException {

        Class<UsuarioResponseDto> dtoClass = UsuarioResponseDto.class;
        Constructor<UsuarioResponseDto> constructor = dtoClass.getDeclaredConstructor();
        constructor.setAccessible(true);

        UsuarioResponseDto dto = constructor.newInstance();

        for (Map.Entry<String, Object> entry : args.entrySet()) {

            if (entry.getKey().equals("idade")) {
                continue;
            }

            Field field = dtoClass.getDeclaredField(entry.getKey());
            field.setAccessible(true);

            if (entry.getKey().equalsIgnoreCase("dataNascimento")) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                field.set(dto, LocalDate.parse(entry.getValue().toString(), formatter));
            } else {
                field.set(dto, entry.getValue());
            }
        }

        return dto;
    }
}
