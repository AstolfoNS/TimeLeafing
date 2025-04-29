package com.astolfo.mapper;

import com.astolfo.model.vo.ArticleDetailsVO;
import com.astolfo.model.vo.ArticleSummaryVO;
import com.astolfo.model.entity.Article;
import com.astolfo.model.vo.TagVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ArticleMapper extends BaseMapper<Article> {

    @Select("""
        SELECT
            tag.*
        FROM
            tag
        JOIN
            article_tag
        ON
            tag.id = article_tag.tag_id
        WHERE
            article_tag.article_id = #{id}
    """)
    List<TagVO> getTagVOsById(@Param("id") Long id);

    @Select("""
        SELECT
            article.*
        FROM
            article
        WHERE
            article.is_public = true
            AND
            article.status = 'ARTICLE'
        ORDER BY
            ${field}
        DESC
    """)
    @Results({
            @Result(property = "id", column = "id"),
            @Result(
                    property = "author",
                    column = "author_id",
                    one = @One(select = "com.astolfo.mapper.UserMapper.getUserVOById")
            ),
            @Result(
                    property = "tags",
                    column = "id",
                    many = @Many(select = "getTagVOsById")
            )
    })
    Page<ArticleSummaryVO> getSummaryArticleVOs(Page<ArticleSummaryVO> page, @Param("field") String field);

    @Select("""
        SELECT
            article.*
        FROM
            article
        WHERE
            article.is_public = true
            AND
            article.status = 'ARTICLE'
        ORDER BY
            ${field}
        DESC
    """)
    @Results({
            @Result(property = "id", column = "id"),
            @Result(
                    property = "author",
                    column = "author_id",
                    one = @One(select = "com.astolfo.mapper.UserMapper.getUserVOById")
            ),
            @Result(
                    property = "tags",
                    column = "id",
                    many = @Many(select = "getTagVOsById")
            )
    })
    Page<ArticleDetailsVO> getDetailsArticleVOs(Page<ArticleDetailsVO> page, @Param("field") String field);

    @Select("""
        SELECT
            article.*
        FROM
            article
        WHERE
            article.is_public = true
            AND
            article.status = 'ARTICLE'
            AND
            article.id = #{id}
    """)
    @Results({
            @Result(property = "id", column = "id"),
            @Result(
                    property = "author",
                    column = "author_id",
                    one = @One(select = "com.astolfo.mapper.UserMapper.getUserVOById")
            ),
            @Result(property = "tags",
                    column = "id",
                    many = @Many(select = "getTagVOsById")
            )
    })
    ArticleDetailsVO getArticleDetailsVOById(@Param("id") Long id);

    @Select("""
        SELECT
            article.*
        FROM
            article
        WHERE
            article.is_public = true
            AND
            article.status = 'ARTICLE'
            AND
            article.id = #{id}
    """)
    @Results({
            @Result(property = "id", column = "id"),
            @Result(
                    property = "author",
                    column = "author_id",
                    one = @One(select = "com.astolfo.mapper.UserMapper.getUserVOById")
            ),
            @Result(
                    property = "tags",
                    column = "id",
                    many = @Many(select = "getTagVOsById")
            )
    })
    ArticleSummaryVO getArticleSummaryVOById(@Param("id") Long id);

}