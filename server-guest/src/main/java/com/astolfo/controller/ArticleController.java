package com.astolfo.controller;

import com.astolfo.common.result.PageResult;
import com.astolfo.common.result.ResponseResult;
import com.astolfo.entity.Article;
import com.astolfo.service.ArticleService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/article")
@RestController
public class ArticleController {

    @Resource
    private ArticleService articleService;

    @GetMapping("/")
    public ResponseResult<PageResult<Article>> fetchAllArticles(
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "size", required = false) Integer size,
            @RequestParam(value = "sort", required = false) String sort
    ) {
        return articleService.fetchAllArticles(page, size, sort);
    }

    @GetMapping("/{articleId}")
    public ResponseResult<Article> getArticleByArticleId(@PathVariable("articleId") Long articleId) {
        return articleService.getArticleByArticleId(articleId);
    }
}
