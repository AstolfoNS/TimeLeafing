package com.astolfo.service.impl;

import com.astolfo.common.constants.ArticleHomepageConstant;
import com.astolfo.common.enums.ArticleSortField;
import com.astolfo.common.enums.ArticleStatus;
import com.astolfo.common.enums.HttpCode;
import com.astolfo.common.result.PageResult;
import com.astolfo.common.result.ResponseResult;
import com.astolfo.model.entity.Article;
import com.astolfo.mapper.ArticleMapper;
import com.astolfo.model.vo.ArticleDetailsVO;
import com.astolfo.model.vo.ArticleSummaryVO;
import com.astolfo.service.ArticleService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Resource
    private ArticleMapper articleMapper;


    private Page<ArticleSummaryVO> createHomepageArticlePage(Integer page, Integer size) {
        if (page == null || page == 0) {
            page = ArticleHomepageConstant.DEFAULT_PAGE;
        }
        if (size == null || size == 0) {
            size = ArticleHomepageConstant.DEFAULT_SIZE;
        }

        return Page.of(page, size);
    }

    @Override
    public ResponseResult<PageResult<ArticleSummaryVO>> getHomepageArticles(
            Integer page,
            Integer size,
            ArticleSortField articleSortField
    ) {
        Page<ArticleSummaryVO> articleSummaryVOPage = createHomepageArticlePage(page, size);

        Page<ArticleSummaryVO> resultPage =
                articleMapper.getHomepageArticles(articleSummaryVOPage, articleSortField.getSortField());

        return ResponseResult.okResult(
                PageResult
                        .<ArticleSummaryVO>builder()
                        .records(resultPage.getRecords())
                        .pages(resultPage.getPages())
                        .total(resultPage.getTotal())
                        .current(resultPage.getCurrent())
                        .size(resultPage.getSize())
                        .build()
        );
    }

    @Override
    public ResponseResult<ArticleDetailsVO> getArticleById(Long id) {
        ArticleDetailsVO articleDetailsVO = articleMapper.getArticleDetailsVOById(id);

        if (articleDetailsVO == null) {
            return ResponseResult.errorResult(HttpCode.ARTICLE_NOT_FOUND);
        } else {
            return ResponseResult.okResult(articleDetailsVO);
        }
    }

}
