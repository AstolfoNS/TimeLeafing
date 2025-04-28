package com.astolfo.service.impl;

import com.astolfo.common.constants.ArticleHomepageConstant;
import com.astolfo.common.enums.HttpCode;
import com.astolfo.common.result.PageResult;
import com.astolfo.common.result.ResponseResult;
import com.astolfo.model.entity.Article;
import com.astolfo.mapper.ArticleMapper;
import com.astolfo.model.vo.ArticleDetailsVO;
import com.astolfo.model.vo.ArticleSummaryVO;
import com.astolfo.service.ArticleService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Resource
    private ArticleMapper articleMapper;


    @Override
    public ResponseResult<PageResult<ArticleSummaryVO>> getHomepageArticles(
            Integer page,
            Integer size,
            String field
    ) {
        if (page == null || page < 1) {
            page = ArticleHomepageConstant.DEFAULT_PAGE;
        }
        if (size == null || size < 1) {
            size = ArticleHomepageConstant.DEFAULT_SIZE;
        }

        return ResponseResult.okResult(PageResult.init(articleMapper.getHomepageArticles(Page.of(page, size), field)));
    }

    @Override
    public ResponseResult<ArticleDetailsVO> getArticleDetailsVOById(Long id) {
        ArticleDetailsVO articleDetailsVO = articleMapper.getArticleDetailsVOById(id);

        if (articleDetailsVO == null) {
            return ResponseResult.errorResult(HttpCode.ARTICLE_NOT_FOUND);
        } else {
            return ResponseResult.okResult(articleDetailsVO);
        }
    }

    @Override
    public ResponseResult<ArticleSummaryVO> getArticleSummaryVOById(Long id) {
        ArticleSummaryVO articleSummaryVO = articleMapper.getArticleSummaryVOById(id);

        if (articleSummaryVO == null) {
            return ResponseResult.errorResult(HttpCode.ARTICLE_NOT_FOUND);
        } else {
            return ResponseResult.okResult(articleSummaryVO);
        }
    }

    @Override
    public ResponseResult<List<String>> getTagNamesByArticleId(Long articleId) {
        if (articleMapper.selectById(articleId) == null) {
            return ResponseResult.errorResult(HttpCode.ARTICLE_NOT_FOUND);
        } else {
            return ResponseResult.okResult(articleMapper.getTagNamesByArticleId(articleId));
        }
    }

}
