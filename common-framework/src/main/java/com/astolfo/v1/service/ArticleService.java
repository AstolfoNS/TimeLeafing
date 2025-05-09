package com.astolfo.v1.service;

import com.astolfo.v1.common.results.PageResult;
import com.astolfo.v1.common.results.ResponseResult;
import com.astolfo.v1.model.entity.Article;
import com.astolfo.v1.model.vo.ArticleDetailsVO;
import com.astolfo.v1.model.vo.ArticleSummaryVO;
import com.astolfo.v1.model.vo.TagVO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface ArticleService extends IService<Article> {
    ResponseResult<PageResult<ArticleDetailsVO>> getDetailsArticleVOs(
            Integer page,
            Integer size,
            String field
    );

    ResponseResult<PageResult<ArticleSummaryVO>> getSummaryArticleVOs(
            Integer page,
            Integer size,
            String field
    );

    ResponseResult<ArticleDetailsVO> getArticleDetailsVOById(Long id);

    ResponseResult<ArticleSummaryVO> getArticleSummaryVOById(Long id);

    ResponseResult<List<TagVO>> getTagVOsById(Long id);

    ResponseResult<Integer> addViewCounts(Long id, Integer count);

}
