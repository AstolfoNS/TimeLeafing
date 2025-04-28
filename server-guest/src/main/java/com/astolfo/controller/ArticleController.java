package com.astolfo.controller;

import com.astolfo.common.enums.ArticleSortField;
import com.astolfo.common.result.PageResult;
import com.astolfo.common.result.ResponseResult;
import com.astolfo.model.vo.ArticleDetailsVO;
import com.astolfo.model.vo.ArticleSummaryVO;
import com.astolfo.service.ArticleService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/article")
@RestController
public class ArticleController {

    @Resource
    private ArticleService articleService;

    @GetMapping("/homepage")
    public ResponseResult<PageResult<ArticleSummaryVO>> fetchAllArticles(
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "size", required = false) Integer size,
            @RequestParam(value = "sort", required = false) String field
    ) {
        return articleService.getHomepageArticles(page, size, ArticleSortField.getByFieldName(field).getSortField());
    }

    @GetMapping("/{id}/details")
    public ResponseResult<ArticleDetailsVO> getArticleDetailsVOById(@PathVariable("id") Long id) {
        return articleService.getArticleDetailsVOById(id);
    }

    @GetMapping("/{id}/summary")
    public ResponseResult<ArticleSummaryVO> getArticleSummaryVOById(@PathVariable("id") Long id) {
        return articleService.getArticleSummaryVOById(id);
    }

    @GetMapping("/{id}/tags")
    public ResponseResult<List<String>> getTagNamesByArticleId(@PathVariable("id") Long articleId) {
        return articleService.getTagNamesByArticleId(articleId);
    }

}
