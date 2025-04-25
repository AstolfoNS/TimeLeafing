package com.astolfo.service;

import com.astolfo.common.enums.ArticleSortField;
import com.astolfo.common.result.PageResult;
import com.astolfo.common.result.ResponseResult;
import com.astolfo.entity.Article;
import com.astolfo.vo.HomepageArticleVO;
import com.baomidou.mybatisplus.extension.service.IService;

public interface ArticleService extends IService<Article> {

    ResponseResult<PageResult<HomepageArticleVO>> fetchAllArticlesOnHomepage(
            Integer page,
            Integer size,
            ArticleSortField articleSortField
    );

    ResponseResult<Article> getArticleByArticleId(Long articleId);
}
