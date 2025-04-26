package com.astolfo.model.dto;

import com.astolfo.model.entity.Article;
import com.astolfo.model.entity.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ArticleTagDTO {

    private Article article;

    private List<Tag> tags;

}
