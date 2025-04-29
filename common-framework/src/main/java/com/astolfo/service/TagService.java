package com.astolfo.service;

import com.astolfo.common.result.PageResult;
import com.astolfo.common.result.ResponseResult;
import com.astolfo.model.entity.Tag;
import com.astolfo.model.vo.ArticleDetailsVO;
import com.astolfo.model.vo.ArticleSummaryVO;
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
