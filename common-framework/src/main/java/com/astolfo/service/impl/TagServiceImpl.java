package com.astolfo.service.impl;

import com.astolfo.common.constants.ArticleSearchPageConstant;
import com.astolfo.common.result.PageResult;
import com.astolfo.common.result.ResponseResult;
import com.astolfo.mapper.TagMapper;
import com.astolfo.model.entity.Tag;
import com.astolfo.model.vo.ArticleDetailsVO;
import com.astolfo.model.vo.ArticleSummaryVO;
import com.astolfo.service.TagService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {

    @Resource
    private TagMapper tagMapper;

    private static <T> Page<T> page(Integer page, Integer size) {
        if (page == null || page < 1) {
            page = ArticleSearchPageConstant.DEFAULT_PAGE;
        }
        if (size == null || size < 1) {
            size = ArticleSearchPageConstant.DEFAULT_SIZE;
        }

        return new Page<>(page, size);
    }

    @Override
    public ResponseResult<PageResult<ArticleSummaryVO>> getArticleSummaryVOsByTagName(
            Integer page,
            Integer size,
            String field,
            String tagName
    ) {
        return ResponseResult.okResult(
                PageResult.init(tagMapper.getArticleSummaryVOsByTagName(page(page, size), field, tagName))
        );
    }

    @Override
    public ResponseResult<PageResult<ArticleDetailsVO>> getArticleDetailsVOsByTagName(
            Integer page,
            Integer size,
            String field,
            String tagName
    ) {
        return ResponseResult.okResult(
                PageResult.init(tagMapper.getArticleDetailsVOsByTagName(page(page, size), field, tagName))
        );
    }

}
