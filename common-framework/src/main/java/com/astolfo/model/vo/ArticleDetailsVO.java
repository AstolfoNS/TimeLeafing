package com.astolfo.model.vo;

import com.astolfo.common.enums.ArticleStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ArticleDetailsVO {

    private Long id;

    private UserVO author;

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

    private List<TagVO> tags;

}
