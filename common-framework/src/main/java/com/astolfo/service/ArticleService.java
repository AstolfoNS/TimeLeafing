package com.astolfo.service;

import com.astolfo.common.enums.ArticleSortField;
import com.astolfo.common.result.PageResult;
import com.astolfo.common.result.ResponseResult;
import com.astolfo.model.entity.Article;
import com.astolfo.model.vo.ArticleDetailsVO;
import com.astolfo.model.vo.ArticleSummaryVO;
import com.baomidou.mybatisplus.extension.service.IService;

public interface ArticleService extends IService<Article> {

    ResponseResult<PageResult<ArticleSummaryVO>> fetchAllArticlesOnHomepage(
            Integer page,
            Integer size,
            ArticleSortField articleSortField
    );

    ResponseResult<ArticleDetailsVO> getArticleByArticleId(Long articleId);
}
