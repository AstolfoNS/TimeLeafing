package com.astolfo.v1.service;

import com.astolfo.v1.common.results.PageResult;
import com.astolfo.v1.common.results.ResponseResult;
import com.astolfo.v1.model.entity.Tag;
import com.astolfo.v1.model.vo.ArticleDetailsVO;
import com.astolfo.v1.model.vo.ArticleSummaryVO;
import com.baomidou.mybatisplus.extension.service.IService;

public interface TagService extends IService<Tag> {

    ResponseResult<PageResult<ArticleSummaryVO>> getArticleSummaryVOsByTagName(
            Integer page,
            Integer size,
            String field,
            String tagName
    );

    ResponseResult<PageResult<ArticleDetailsVO>> getArticleDetailsVOsByTagName(
            Integer page,
            Integer size,
            String field,
            String tagName
    );
}
