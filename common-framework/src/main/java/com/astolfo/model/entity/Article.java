package com.astolfo.model.entity;

import com.astolfo.common.enums.ArticleStatus;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@TableName("article")
@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Article {

    @TableId
    private Long articleId;                                                                                             // 文章ID

    private String authorName;                                                                                          // 作者姓名

    private String title;                                                                                               // 标题

    private String content;                                                                                             // 内容

    private String summary;                                                                                             // 摘要

    private String coverImage;                                                                                          // 封面

    private ArticleStatus status;                                                                                       // 状态

    private Boolean isDeleted;                                                                                          // 是否被删除

    private Boolean isPublic;                                                                                           // 是否公开

    private Date createTime;                                                                                            // 创建时间

    private Date updateTime;                                                                                            // 更新时间

    private Long viewCounts;                                                                                            // 访问数量

    private Long likeCounts;                                                                                            // 点赞数量

    @TableField(exist = false)
    private List<String> tags;
}
