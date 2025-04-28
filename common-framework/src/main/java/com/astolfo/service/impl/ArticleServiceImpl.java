package com.astolfo.service.impl;

import com.astolfo.common.constants.ArticleHomepageConstant;
import com.astolfo.common.enums.ArticleStatus;
import com.astolfo.common.enums.HttpCode;
import com.astolfo.common.result.PageResult;
import com.astolfo.common.result.ResponseResult;
import com.astolfo.model.entity.Article;
import com.astolfo.mapper.ArticleMapper;
import com.astolfo.model.vo.ArticleDetailsVO;
import com.astolfo.model.vo.ArticleSummaryVO;
import com.astolfo.model.vo.UserVO;
import com.astolfo.model.vo.TagVO;
import com.astolfo.service.ArticleService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Resource
    private ArticleMapper articleMapper;


    private static <T> Page<T> page(Integer page, Integer size) {
        if (page == null || page < 1) {
            page = ArticleHomepageConstant.DEFAULT_PAGE;
        }
        if (size == null || size < 1) {
            size = ArticleHomepageConstant.DEFAULT_SIZE;
        }

        return new Page<>(page, size);
    }

    @Override
    public ResponseResult<PageResult<ArticleDetailsVO>> getDetailsArticles(
            Integer page,
            Integer size,
            String field
    ) {
        return ResponseResult.okResult(PageResult.init(articleMapper.getDetailsArticles(page(page, size), field)));
    }

    @Override
    public ResponseResult<PageResult<ArticleSummaryVO>> getSummaryArticles(
            Integer page,
            Integer size,
            String field
    ) {
        return ResponseResult.okResult(PageResult.init(articleMapper.getSummaryArticles(page(page, size), field)));
    }

    public static <T> ResponseResult<T> checkArticleResult(T result) {
        if (result == null) {
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

        if (article == null || article.getIsPublic() == Boolean.FALSE || article.getStatus() == ArticleStatus.DRAFT) {
            return ResponseResult.errorResult(HttpCode.ARTICLE_NOT_FOUND);
        } else {
            return ResponseResult.okResult(articleMapper.getTagVOsById(id));
        }
    }

    @Override
    public ResponseResult<UserVO> getUserVOById(Long id) {
        if (articleMapper.selectById(id) == null) {
            return ResponseResult.errorResult(HttpCode.ARTICLE_NOT_FOUND);
        } else {
            return ResponseResult.okResult(articleMapper.getUserVOById(id));
        }
    }

}
