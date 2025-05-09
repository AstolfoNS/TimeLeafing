package com.astolfo.v1.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ArticleSummaryVO {

    private UserVO author;

    private String title;

    private String summary;

    private String coverImage;

    private Long viewCounts;

    private Long likeCounts;

    private List<TagVO> tagVOs;

}
