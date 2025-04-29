package com.astolfo.mapper;

import com.astolfo.model.entity.Tag;
import com.astolfo.model.vo.ArticleDetailsVO;
import com.astolfo.model.vo.ArticleSummaryVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.*;

@Mapper
public interface TagMapper extends BaseMapper<Tag> {

    @Select("""
        SELECT
            article.*
        FROM
            article
        JOIN
            article_tag
        ON
            article.id = article_tag.article_id
        JOIN
            tag
        ON
            article_tag.tag_id = tag.id
        WHERE
            tag.tag_name = #{tagName}
            AND
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
                    many = @Many(select = "com.astolfo.mapper.ArticleMapper.getTagVOsById")
            )
    })
    Page<ArticleSummaryVO> getArticleSummaryVOsByTagName(
            Page<ArticleSummaryVO> page,
            @Param("field") String field,
            @Param("tagName") String tagName
    );

    @Select("""
        SELECT
            article.*
        FROM
            article
        JOIN
            article_tag
        ON
            article.id = article_tag.article_id
        JOIN
            tag
        ON
            article_tag.tag_id = tag.id
        WHERE
            tag.tag_name = #{tagName}
            AND
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
                    many = @Many(select = "com.astolfo.mapper.ArticleMapper.getTagVOsById")
            )
    })
    Page<ArticleDetailsVO> getArticleDetailsVOsByTagName(
            Page<ArticleDetailsVO> page,
            @Param("field") String field,
            @Param("tagName") String tagName
    );

}
