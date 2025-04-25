package com.astolfo.service.impl;

import com.astolfo.common.enums.ArticleSortField;
import com.astolfo.common.enums.ArticleStatus;
import com.astolfo.common.enums.HttpCode;
import com.astolfo.common.result.PageResult;
import com.astolfo.common.result.ResponseResult;
import com.astolfo.common.utils.VOBeanMapperUtil;
import com.astolfo.entity.Article;
import com.astolfo.mapper.ArticleMapper;
import com.astolfo.service.ArticleService;
import com.astolfo.vo.HomepageArticleVO;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    private static final Integer DEFAULT_HOMEPAGE_PAGE = 1;                                                             // homepage默认页码

    private static final Integer DEFAULT_HOMEPAGE_SIZE = 6;                                                             // homepage每页数量

    @Resource
    private ArticleMapper articleMapper;


    private Page<Article> createHomepageArticlePage(Integer page, Integer size) {
        if (page == null || page == 0) {
            page = DEFAULT_HOMEPAGE_PAGE;
        }
        if (size == null || size == 0) {
            size = DEFAULT_HOMEPAGE_SIZE;
        }

        return Page.of(page, size);
    }

    @Override
    public ResponseResult<PageResult<HomepageArticleVO>> fetchAllArticlesOnHomepage(
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

        return ResponseResult.okResult(
                PageResult
                        .<HomepageArticleVO>builder()
                        .records(VOBeanMapperUtil.toVOList(articlePage.getRecords(), HomepageArticleVO.class))          // Article转化为HomepageArticleVO
                        .pages(articlePage.getPages())                                                                  // 总页数
                        .total(articlePage.getTotal())                                                                  // 总数
                        .current(articlePage.getCurrent())                                                              // 当前页数
                        .size(articlePage.getSize())                                                                    // 每页数量
                        .build()
        );
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
