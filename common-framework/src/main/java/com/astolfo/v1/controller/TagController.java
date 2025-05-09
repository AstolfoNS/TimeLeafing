package com.astolfo.v1.controller;

import com.astolfo.v1.common.enums.ArticleSortField;
import com.astolfo.v1.common.results.PageResult;
import com.astolfo.v1.common.results.ResponseResult;
import com.astolfo.v1.model.vo.ArticleDetailsVO;
import com.astolfo.v1.model.vo.ArticleSummaryVO;
import com.astolfo.v1.service.TagService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tag")
public class TagController {

    @Resource
    private TagService tagService;


    @GetMapping("/{tagName}/summary")
    public ResponseResult<PageResult<ArticleSummaryVO>> getSummaryArticleVOs(
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "size", required = false) Integer size,
            @RequestParam(value = "sort", required = false) String field,
            @PathVariable("tagName") String tagName
    ) {
        return tagService.getArticleSummaryVOsByTagName(page, size, ArticleSortField.toSortField(field), tagName);
    }

    @GetMapping("/{tagName}/details")
    public ResponseResult<PageResult<ArticleDetailsVO>> getDetailsArticleVOs(
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "size", required = false) Integer size,
            @RequestParam(value = "sort", required = false) String field,
            @PathVariable("tagName") String tagName
    ) {
        return tagService.getArticleDetailsVOsByTagName(page, size, ArticleSortField.toSortField(field), tagName);
    }

}
