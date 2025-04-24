package com.astolfo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class HomepageArticleVO {

    private Long articleId;

    private String authorName;

    private String title;

    private String summary;

    private String coverImage;

    private Date createTime;

    private Long viewCounts;

    private Long likeCounts;
}
