package com.astolfo.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ArticleStatus {

    DRAFT("DRAFT"),
    ARTICLE("ARTICLE");


    final String status;

}

