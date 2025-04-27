package com.astolfo.model.vo;

import com.astolfo.common.enums.ArticleStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ArticleDetailsVO {

    private Long id;

    private String authorName;

    private String title;

    private String content;

    private String summary;

    private String coverImage;

    private ArticleStatus status;

    private Boolean isDeleted;

    private Boolean isPublic;

    private Date createTime;

    private Date updateTime;

    private Long viewCounts;

    private Long likeCounts;

    private List<String> tags;

}
