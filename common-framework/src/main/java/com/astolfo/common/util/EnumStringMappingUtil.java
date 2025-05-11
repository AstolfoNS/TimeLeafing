package com.astolfo.common.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class EnumStringMappingUtil {

    public static <E extends Enum<E>> Map<String, E> buildMapping(Class<E> enumClass, String valueMethodName) {
        if (!enumClass.isEnum()) {
            throw new IllegalArgumentException("Class must be an enum type");
        }

        Method valueMethod = getMethod(enumClass, valueMethodName);

        return Arrays.stream(enumClass.getEnumConstants()).collect(Collectors.toMap(enumConstant -> {
            try {
                return (String) valueMethod.invoke(enumConstant);
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException("Failed to get string value for enum constant " + enumConstant.name(), e);
            }
        }, Function.identity()));
    }

    private static <E extends Enum<E>> Method getMethod(Class<E> enumClass, String valueMethodName) {
        Method valueMethod;

        try {
            valueMethod = enumClass.getMethod(valueMethodName);
        } catch (NoSuchMethodException e) {
            throw new IllegalArgumentException("Enum class " + enumClass.getName() + ", must have a public method named '" + valueMethodName + "'", e);
        }

        if (!String.class.equals(valueMethod.getReturnType())) {
            throw new IllegalArgumentException("Method '" + valueMethodName + "' in enum class " + enumClass.getName() + " must return a String type");
        }
        return valueMethod;
    }
}