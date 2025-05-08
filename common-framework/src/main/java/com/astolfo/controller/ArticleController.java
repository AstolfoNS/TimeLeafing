package com.astolfo.controller;

import com.astolfo.common.enums.ArticleSortField;
import com.astolfo.common.results.PageResult;
import com.astolfo.common.results.ResponseResult;
import com.astolfo.model.vo.ArticleDetailsVO;
import com.astolfo.model.vo.ArticleSummaryVO;
import com.astolfo.model.vo.TagVO;
import com.astolfo.service.ArticleService;
import jakarta.annotation.Resource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RequestMapping("/article")
@RestController
public class ArticleController {

    @Resource
    private ArticleService articleService;


    @GetMapping("/summary")
    public ResponseResult<PageResult<ArticleSummaryVO>> getSummaryArticleVOs(
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "size", required = false) Integer size,
            @RequestParam(value = "sort", required = false) String field
    ) {
        return articleService.getSummaryArticleVOs(page, size, ArticleSortField.toSortField(field));
    }

    @PreAuthorize("hasAuthority('admin')")
    @GetMapping("/details")
    public ResponseResult<PageResult<ArticleDetailsVO>> getDetailsArticleVOs(
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "size", required = false) Integer size,
            @RequestParam(value = "sort", required = false) String field
    ) {
        return articleService.getDetailsArticleVOs(page, size, ArticleSortField.toSortField(field));
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

    @PutMapping("/{id}/add-view-counts")
    public ResponseResult<Integer> addViewCounts(@PathVariable("id") Long id, @RequestParam("count") Integer count) {
        return articleService.addViewCounts(id, Objects.isNull(count) ? 0 : count);
    }

}
