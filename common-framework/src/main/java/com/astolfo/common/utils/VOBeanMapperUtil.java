package com.astolfo.common.utils;

import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class VOBeanMapperUtil {

    public static <T, V> V entityToVO(T entity, Class<V> entityVOClass) {
        try {
            V entityVO = entityVOClass.getDeclaredConstructor().newInstance();

            BeanUtils.copyProperties(entity, entityVO);

            return entityVO;
        } catch (Exception exception) {
            throw new RuntimeException("Failed to map entity to VO", exception);
        }
    }

    public static <T, V> List<V> toVOList(List<T> entityList, Class<V> entityVOClass) {
        return entityList
                .stream()
                .map(entity -> entityToVO(entity, entityVOClass))
                .collect(Collectors.toList());
    }

    public static <T, V> Set<V> toVOSet(Set<T> entitySet, Class<V> entityVOClass) {
        return entitySet
                .stream()
                .map(entity -> entityToVO(entity, entityVOClass))
                .collect(Collectors.toSet());
    }
}
