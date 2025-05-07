package com.astolfo.mapper.provider;

import org.apache.ibatis.annotations.Param;

public class ArticleSqlProvider {

    public String getArticles(@Param("field") String field) {
        return """
            SELECT
                article.*
            FROM
                article
            WHERE
                article.is_public = true
                AND
                article.stage = 'PUBLISHED'
                AND
                article.enabled = true
            ORDER BY
                ${field}
            DESC
        """;
    }

    public String getArticleById(@Param("id") Long id) {
        return """
            SELECT
                article.*
            FROM
                article
            WHERE
                article.is_public = true
                AND
                article.stage = 'PUBLISHED'
                AND
                article.enabled = true
                AND
                article.id = #{id}
        """;
    }

    public String getTagVOsById(@Param("id") Long id) {
        return """
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
        """;
    }

    public String addViewCounts(@Param("id") Long id, @Param("count") Integer count) {
        return """
            UPDATE
                article
            SET
                article.view_counts = article.view_counts + #{count}
            WHERE
                article.id = #{id}
        """;
    }

}
