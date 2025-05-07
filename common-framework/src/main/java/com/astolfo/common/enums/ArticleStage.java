package com.astolfo.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ArticleStage {

    DRAFT("DRAFT"),
    PENDING("PENDING"),
    PUBLISHED("PUBLISHED");


    private final String stageName;

}

