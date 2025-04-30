package com.astolfo.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ArticleSummaryVO {

    private Long id;

    private UserVO author;

    private String title;

    private String summary;

    private String coverImage;

    private Date createTime;

    private Date updateTime;

    private Long viewCounts;

    private Long likeCounts;

    private List<TagVO> tags;

}
