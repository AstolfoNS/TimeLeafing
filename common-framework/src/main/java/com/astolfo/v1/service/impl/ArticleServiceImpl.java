package com.astolfo.v1.service.impl;

import com.astolfo.v1.common.constants.ArticleHomepageConstant;
import com.astolfo.v1.common.enums.ArticleStage;
import com.astolfo.v1.common.enums.HttpCode;
import com.astolfo.v1.common.results.PageResult;
import com.astolfo.v1.common.results.ResponseResult;
import com.astolfo.v1.model.entity.Article;
import com.astolfo.v1.mapper.ArticleMapper;
import com.astolfo.v1.model.vo.ArticleDetailsVO;
import com.astolfo.v1.model.vo.ArticleSummaryVO;
import com.astolfo.v1.model.vo.TagVO;
import com.astolfo.v1.service.ArticleService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Resource
    private ArticleMapper articleMapper;


    private static <T> Page<T> page(Integer page, Integer size) {
        if (Objects.isNull(page) || page < 1) {
            page = ArticleHomepageConstant.DEFAULT_PAGE;
        }
        if (Objects.isNull(size) || size < 1) {
            size = ArticleHomepageConstant.DEFAULT_SIZE;
        }

        return new Page<>(page, size);
    }

    @Override
    public ResponseResult<PageResult<ArticleDetailsVO>> getDetailsArticleVOs(
            Integer page,
            Integer size,
            String field
    ) {
        return ResponseResult.okResult(PageResult.init(articleMapper.getDetailsArticleVOs(page(page, size), field)));
    }

    @Override
    public ResponseResult<PageResult<ArticleSummaryVO>> getSummaryArticleVOs(
            Integer page,
            Integer size,
            String field
    ) {
        return ResponseResult.okResult(PageResult.init(articleMapper.getSummaryArticleVOs(page(page, size), field)));
    }

    public static <T> ResponseResult<T> checkArticleResult(T result) {
        if (Objects.isNull(result)) {
            return ResponseResult.errorResult(HttpCode.ARTICLE_NOT_FOUND);
        } else {
            return ResponseResult.okResult(result);
        }
    }

    @Override
    public ResponseResult<ArticleDetailsVO> getArticleDetailsVOById(Long id) {
        return checkArticleResult(articleMapper.getArticleDetailsVOById(id));
    }

    @Override
    public ResponseResult<ArticleSummaryVO> getArticleSummaryVOById(Long id) {
        return checkArticleResult(articleMapper.getArticleSummaryVOById(id));
    }

    @Override
    public ResponseResult<List<TagVO>> getTagVOsById(Long id) {
        Article article = articleMapper.selectById(id);

        if (Objects.isNull(article) || !article.getIsPublic() || article.getStage() != ArticleStage.PUBLISHED) {
            return ResponseResult.errorResult(HttpCode.ARTICLE_NOT_FOUND);
        } else {
            return ResponseResult.okResult(articleMapper.getTagVOsById(id));
        }
    }

    @Override
    public ResponseResult<Integer> addViewCounts(Long id, Integer count) {
        return ResponseResult.okResult(articleMapper.addViewCounts(id, count));
    }

}
