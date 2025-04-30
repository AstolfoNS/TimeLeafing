package com.astolfo.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ArticleStatus {

    DRAFT("draft"),
    ARTICLE("article");


    private final String status;

}

