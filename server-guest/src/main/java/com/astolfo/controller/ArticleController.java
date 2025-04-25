package com.astolfo.controller;

import com.astolfo.common.enums.ArticleSortField;
import com.astolfo.common.result.PageResult;
import com.astolfo.common.result.ResponseResult;
import com.astolfo.entity.Article;
import com.astolfo.service.ArticleService;
import com.astolfo.vo.HomepageArticleVO;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/article")
@RestController
public class ArticleController {

    @Resource
    private ArticleService articleService;

    @GetMapping("/homepage")
    public ResponseResult<PageResult<HomepageArticleVO>> fetchAllArticles(
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "size", required = false) Integer size,
            @RequestParam(value = "sort", required = false) String sortName
    ) {
        return articleService.fetchAllArticlesOnHomepage(page, size, ArticleSortField.getByFieldName(sortName));
    }

    @GetMapping("/{articleId}")
    public ResponseResult<Article> getArticleByArticleId(@PathVariable("articleId") Long articleId) {
        return articleService.getArticleByArticleId(articleId);
    }
}
