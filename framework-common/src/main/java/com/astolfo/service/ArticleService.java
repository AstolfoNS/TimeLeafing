package com.astolfo.service;

import com.astolfo.common.result.PageResult;
import com.astolfo.common.result.ResponseResult;
import com.astolfo.entity.Article;
import com.baomidou.mybatisplus.extension.service.IService;

public interface ArticleService extends IService<Article> {

    ResponseResult<PageResult<Article>> fetchAllArticles(Integer page, Integer size, String sort);

    ResponseResult<Article> getArticleByArticleId(Long articleId);
}
