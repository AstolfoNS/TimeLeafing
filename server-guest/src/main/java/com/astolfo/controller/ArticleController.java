package com.astolfo.controller;

import com.astolfo.common.enums.ArticleSortField;
import com.astolfo.common.result.PageResult;
import com.astolfo.common.result.ResponseResult;
import com.astolfo.model.vo.ArticleDetailsVO;
import com.astolfo.model.vo.ArticleSummaryVO;
import com.astolfo.model.vo.TagVO;
import com.astolfo.service.ArticleService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/article")
@RestController
public class ArticleController {

    @Resource
    private ArticleService articleService;


    @GetMapping("/summary")
    public ResponseResult<PageResult<ArticleSummaryVO>> getSummaryArticles(
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "size", required = false) Integer size,
            @RequestParam(value = "sort", required = false) String field
    ) {
        return articleService.getSummaryArticles(page, size, ArticleSortField.getByFieldName(field).getSortField());
    }

    @GetMapping("/details")
    public ResponseResult<PageResult<ArticleDetailsVO>> getDetailsArticles(
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "size", required = false) Integer size,
            @RequestParam(value = "sort", required = false) String field
    ) {
        return articleService.getDetailsArticles(page, size, ArticleSortField.getByFieldName(field).getSortField());
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
    public ResponseResult<List<TagVO>> getTagVOsById(@PathVariable("id") Long id) {
        return articleService.getTagVOsById(id);
    }

}
