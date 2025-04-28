package com.astolfo.mapper;

import com.astolfo.model.vo.ArticleDetailsVO;
import com.astolfo.model.vo.ArticleSummaryVO;
import com.astolfo.model.entity.Article;
import com.astolfo.model.vo.TagVO;
import com.astolfo.model.vo.UserVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ArticleMapper extends BaseMapper<Article> {

    @Select("""
        SELECT
            tag.*,
        FROM
            tag
        JOIN
            article_tag
        ON
            tag.id = article_tag.tag_id
        WHERE
            article_tag.article_id = #{articleId}
    """)
    List<TagVO> getTagVOListById(@Param("articleId") Long articleId);

    @Select("""
        SELECT
            user.*,
        FROM
            user
        JOIN
            article
        ON
            user.id = article.author_id
        WHERE
            article.id = #{articleId}
    """)
    UserVO getUserVOById(@Param("articleId") Long articleId);

    @Select("""
        SELECT
            article.*,
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
            @Result(property = "author", column = "id", one = @One(select = "getUserVOByArticleId")),
            @Result(property = "tags", column = "id", many = @Many(select = "getTagVOsByArticleId"))
    })
    Page<ArticleSummaryVO> getSummaryArticles(Page<ArticleSummaryVO> page, @Param("sortField") String sortField);

    @Select("""
        SELECT
            article.*,
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
            @Result(property = "author", column = "id", one = @One(select = "getUserVOByArticleId")),
            @Result(property = "tags", column = "id", many = @Many(select = "getTagVOsByArticleId"))
    })
    Page<ArticleDetailsVO> getDetailsArticles(Page<ArticleDetailsVO> page, @Param("sortField") String sortField);

    @Select("""
        SELECT
            article.*,
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
            @Result(property = "author", column = "id", one = @One(select = "getUserVOByArticleId")),
            @Result(property = "tags", column = "id", many = @Many(select = "getTagVOsByArticleId"))
    })
    ArticleDetailsVO getArticleDetailsVOById(@Param("id") Long id);

    @Select("""
        SELECT
            article.*,
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
            @Result(property = "author", column = "id", one = @One(select = "getUserVOByArticleId")),
            @Result(property = "tags", column = "id", many = @Many(select = "getTagVOsByArticleId"))
    })
    ArticleSummaryVO getArticleSummaryVOById(@Param("id") Long id);

}