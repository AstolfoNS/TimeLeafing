package com.astolfo.model.entity;

import com.astolfo.common.enums.ArticleStage;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@TableName("article")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Article {

    @TableId
    private Long id;

    private String authorId;

    private String title;

    private String summary;

    private String content;

    private String coverImage;

    private ArticleStage stage;

    private Boolean isPublic;

    private Long viewCounts;

    private Long likeCounts;

    private Boolean status;

    private Date createTime;

    private Date updateTime;

    private Boolean isDeleted;

}
