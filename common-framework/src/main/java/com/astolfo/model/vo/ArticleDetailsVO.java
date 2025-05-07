package com.astolfo.model.vo;

import com.astolfo.common.enums.ArticleStage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ArticleDetailsVO {

    private UserVO author;

    private String title;

    private String summary;

    private String content;

    private String coverImage;

    private ArticleStage stage;

    private Boolean isPublic;

    private Long viewCounts;

    private Long likeCounts;

    private List<TagVO> tagVOs;

    private Boolean enabled;

    private Date createTime;

    private Date updateTime;

    private Boolean isDeleted;


}
