package com.astolfo.common.util;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
public class EnumStringMappingUtil {

    public static <E extends Enum<E>> Map<String, E> buildMapping(Class<E> enumClass, String valueMethodName) {
        if (!enumClass.isEnum()) {
            log.error("该类不是枚举类型。Class：{}", enumClass);

            throw new IllegalArgumentException("该类必须是枚举类型：" + enumClass);
        }

        Method valueMethod = getMethod(enumClass, valueMethodName);

        return Arrays.stream(enumClass.getEnumConstants()).collect(Collectors.toMap(enumConstant -> {
            try {
                return (String) valueMethod.invoke(enumConstant);
            } catch (IllegalAccessException | InvocationTargetException exception) {
                log.error("获取枚举类型的string name失败：{}", exception.getMessage());

                throw new RuntimeException("获取枚举类型的string name失败" + enumConstant.name(), exception);
            }
        }, Function.identity()));
    }

    private static <E extends Enum<E>> Method getMethod(Class<E> enumClass, String valueMethodName) {
        Method valueMethod;

        try {
            valueMethod = enumClass.getMethod(valueMethodName);
        } catch (NoSuchMethodException exception) {
            log.error("枚举类型必须存在一个public方法去获取string name。Class：{}，methodName：{}", enumClass,valueMethodName);

            throw new IllegalArgumentException("枚举类型：" + enumClass.getName() + ", 必须有一个public方法：" + valueMethodName, exception);
        }

        if (!String.class.equals(valueMethod.getReturnType())) {
            log.error("枚举类型的返回值不为String。Class：{}，methodName：{}", enumClass, valueMethodName);

            throw new IllegalArgumentException("枚举类型：" + enumClass.getName() + "，获取string name的方法：" + valueMethodName + "返回值不为String");
        }
        return valueMethod;
    }
}