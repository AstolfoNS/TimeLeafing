package com.astolfo.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@AllArgsConstructor
@Getter
public enum ArticleSortField {

    LIKE_COUNTS("likeCounts", "article.like_counts"),
    VIEW_COUNTS("viewCounts", "article.view_counts"),
    CREATE_TIME("createTime", "article.create_time"),;


    private final String fieldName;

    private final String sortField;

    private static final Map<String, ArticleSortField> ENUM_MAP;


    static {
        ENUM_MAP = Stream.of(values()).collect(Collectors.toMap(ArticleSortField::getFieldName, Function.identity()));
    }

    public static ArticleSortField getByFieldName(String fieldName) {
        return ENUM_MAP.getOrDefault(fieldName, CREATE_TIME);
    }

    public static String toSortField(String field) {
        return getByFieldName(field).getSortField();
    }

}
