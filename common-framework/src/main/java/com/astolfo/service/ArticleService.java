package com.astolfo.service;

import com.astolfo.common.result.PageResult;
import com.astolfo.common.result.ResponseResult;
import com.astolfo.model.entity.Article;
import com.astolfo.model.vo.ArticleDetailsVO;
import com.astolfo.model.vo.ArticleSummaryVO;
import com.astolfo.model.vo.UserVO;
import com.astolfo.model.vo.TagVO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface ArticleService extends IService<Article> {
    ResponseResult<PageResult<ArticleDetailsVO>> getDetailsArticles(
            Integer page,
            Integer size,
            String field
    );

    ResponseResult<PageResult<ArticleSummaryVO>> getSummaryArticles(
            Integer page,
            Integer size,
            String field
    );

    ResponseResult<ArticleDetailsVO> getArticleDetailsVOById(Long id);

    ResponseResult<ArticleSummaryVO> getArticleSummaryVOById(Long id);

    ResponseResult<List<TagVO>> getTagVOsById(Long id);

    ResponseResult<UserVO> getUserVOById(Long id);

}
