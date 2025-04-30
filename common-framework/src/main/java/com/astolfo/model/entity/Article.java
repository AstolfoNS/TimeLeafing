package com.astolfo.model.entity;

import com.astolfo.common.enums.ArticleStatus;
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

}
