package com.astolfo.service.impl;

import com.astolfo.common.constants.ArticleHomepageConstant;
import com.astolfo.common.enums.ArticleSortField;
import com.astolfo.common.enums.ArticleStatus;
import com.astolfo.common.enums.HttpCode;
import com.astolfo.common.result.PageResult;
import com.astolfo.common.result.ResponseResult;
import com.astolfo.mapper.TagMapper;
import com.astolfo.model.entity.Article;
import com.astolfo.mapper.ArticleMapper;
import com.astolfo.service.ArticleService;
import com.astolfo.model.vo.ArticleDetailsVO;
import com.astolfo.model.vo.ArticleSummaryVO;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Resource
    private ArticleMapper articleMapper;

    @Resource
    private TagMapper tagMapper;


    private Page<Article> createHomepageArticlePage(Integer page, Integer size) {
        if (page == null || page == 0) {
            page = ArticleHomepageConstant.DEFAULT_PAGE;
        }
        if (size == null || size == 0) {
            size = ArticleHomepageConstant.DEFAULT_SIZE;
        }

        return Page.of(page, size);
    }

    @Override
    public ResponseResult<PageResult<ArticleSummaryVO>> fetchAllArticlesOnHomepage(
            Integer page,
            Integer size,
            ArticleSortField articleSortField
    ) {
        LambdaQueryWrapper<Article> wrapper = new LambdaQueryWrapper<>();

        wrapper
                .orderByDesc(articleSortField.getSortMethod())                                                          // 指定wrapper排序方式
                .eq(Article::getStatus, ArticleStatus.ARTICLE)                                                          // 首页文章为已发布
                .eq(Article::getIsPublic, true);                                                                        // 首页文章为公开

        Page<Article> articlePage = createHomepageArticlePage(page, size);

        this.page(articlePage, wrapper);

        List<Article> articleList = articlePage.getRecords();

        return ResponseResult.okResult(
                PageResult
                        .<ArticleSummaryVO>builder()
                        .records()
                        .pages(articlePage.getPages())                                                                  // 总页数
                        .total(articlePage.getTotal())                                                                  // 总数
                        .current(articlePage.getCurrent())                                                              // 当前页数
                        .size(articlePage.getSize())                                                                    // 每页数量
                        .build()
        );
    }

    @Override
    public ResponseResult<ArticleDetailsVO> getArticleByArticleId(Long articleId) {
        LambdaQueryWrapper<Article> wrapper = new LambdaQueryWrapper<>();

        wrapper
                .eq(Article::getIsPublic, true)
                .eq(Article::getStatus, ArticleStatus.ARTICLE)
                .eq(Article::getArticleId, articleId);

        Article article = articleMapper.selectOne(wrapper);

        if (article == null) {
            return ResponseResult.errorResult(HttpCode.ARTICLE_NOT_FOUND);
        } else {
            return ResponseResult.okResult();
        }
    }

}
