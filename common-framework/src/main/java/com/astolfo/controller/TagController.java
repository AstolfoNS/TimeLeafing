package com.astolfo.controller;

import com.astolfo.common.enums.ArticleSortField;
import com.astolfo.common.result.PageResult;
import com.astolfo.common.result.ResponseResult;
import com.astolfo.model.vo.ArticleDetailsVO;
import com.astolfo.model.vo.ArticleSummaryVO;
import com.astolfo.service.TagService;
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
