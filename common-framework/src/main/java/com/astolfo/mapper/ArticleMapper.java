package com.astolfo.mapper;

import com.astolfo.model.vo.ArticleDetailsVO;
import com.astolfo.model.vo.ArticleSummaryVO;
import com.astolfo.model.entity.Article;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ArticleMapper extends BaseMapper<Article> {

    @Select("""
        SELECT
            article.*,
            user.username AS author_name
        FROM
            article
        JOIN
            user
        ON
            article.author_id = user.id
        WHERE
            article.is_public = true
            AND
            article.status = 'ARTICLE'
        ORDER BY
            ${sortField}
        DESC
    """)
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "tags", column = "id", many = @Many(select = "getTagNamesByArticleId"))
    })
    Page<ArticleSummaryVO> getHomepageArticles(Page<ArticleSummaryVO> page, @Param("sortField") String sortField);

    @Select("""
        SELECT
            article.*,
            user.username AS author_name
        FROM
            article
        JOIN
            user
        ON
            article.author_id = user.id
        WHERE
            article.is_public = true
            AND
            article.status = 'ARTICLE'
            AND
            article.id = #{id}
    """)
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "tags", column = "id", many = @Many(select = "getTagNamesByArticleId"))
    })
    ArticleDetailsVO getArticleDetailsVOById(@Param("id") Long id);

    @Select("""
        SELECT
            tag.tag_name
        FROM
            tag
        JOIN
            article_tag
        ON
            tag.id = article_tag.tag_id
        WHERE
            article_tag.article_id = #{articleId}
    """)
    List<String> getTagNamesByArticleId(@Param("articleId") Long articleId);

}