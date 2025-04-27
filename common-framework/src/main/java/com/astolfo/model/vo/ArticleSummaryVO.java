package com.astolfo.model.vo;

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
public class ArticleSummaryVO {

    private Long id;

    private String authorName;

    private String title;

    private String summary;

    private String coverImage;

    private Date createTime;

    private Long viewCounts;

    private Long likeCounts;

    private List<String> tags;

}
