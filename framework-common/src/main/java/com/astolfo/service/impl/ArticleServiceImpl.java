package com.astolfo.service.impl;

import com.astolfo.common.enums.ArticleStatus;
import com.astolfo.common.enums.HttpCode;
import com.astolfo.common.result.PageResult;
import com.astolfo.common.result.ResponseResult;
import com.astolfo.entity.Article;
import com.astolfo.mapper.ArticleMapper;
import com.astolfo.service.ArticleService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Resource
    private ArticleMapper articleMapper;

    @Override
    public ResponseResult<PageResult<Article>> fetchAllArticles(Integer page, Integer size, String sort) {
        LambdaQueryWrapper<Article> wrapper = new LambdaQueryWrapper<>();

        Map<String, SFunction<Article, ?>> sortFieldMap = Map.of(
                "likeCounts", Article::getLikeCounts,
                "viewCounts", Article::getViewCounts,
                "createTime", Article::getCreateTime
        );

        wrapper
                .eq(Article::getStatus, ArticleStatus.ARTICLE)
                .eq(Article::getIsPublic, true)
                .orderByDesc(sortFieldMap.getOrDefault(sort, Article::getCreateTime));

        if (page == null || page == 0) {
            page = 1;                                                                                                   // 默认页数
        }
        if (size == null || size == 0) {
            size = 6;                                                                                                   // 默认数量
        }

        Page<Article> articlePage = Page.of(page, size);

        this.page(articlePage, wrapper);

        return ResponseResult.okResult(
                PageResult
                        .<Article>builder()
                        .records(articlePage.getRecords())
                        .pages(articlePage.getPages())
                        .total(articlePage.getTotal())
                        .current(articlePage.getCurrent())
                        .size(articlePage.getSize())
                        .build());
    }

    @Override
    public ResponseResult<Article> getArticleByArticleId(Long articleId) {
        LambdaQueryWrapper<Article> wrapper = new LambdaQueryWrapper<>();

        wrapper
                .eq(Article::getIsPublic, true)
                .eq(Article::getStatus, ArticleStatus.ARTICLE)
                .eq(Article::getArticleId, articleId);

        Article article = articleMapper.selectOne(wrapper);

        if (article == null) {
            return ResponseResult.errorResult(HttpCode.ARTICLE_NOT_FOUND);
        } else {
            return ResponseResult.okResult(article);
        }
    }

}
