package com.astolfo.common.enums;

import com.astolfo.model.entity.Article;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@AllArgsConstructor
@Getter
public enum ArticleSortField {

    LIKE_COUNTS("likeCounts", Article::getLikeCounts),
    VIEW_COUNTS("viewCounts", Article::getViewCounts),
    CREATE_TIME("createTime", Article::getCreateTime);

    public final String fieldName;

    public final SFunction<Article, ?> sortMethod;

    private static final Map<String, ArticleSortField> ENUM_MAP;


    static {
        ENUM_MAP = Stream.of(values()).collect(Collectors.toMap(ArticleSortField::getFieldName, Function.identity()));
    }

    public static ArticleSortField getByFieldName(String fieldName) {
        return ENUM_MAP.getOrDefault(fieldName, CREATE_TIME);
    }

}
