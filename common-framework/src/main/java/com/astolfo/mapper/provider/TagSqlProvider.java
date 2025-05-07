package com.astolfo.mapper.provider;

import org.apache.ibatis.annotations.Param;

public class TagSqlProvider {

    public String getArticlesByTagName(@Param("tagName") String tagName, @Param("field") String field) {
        return """
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
                article.status = 'PUBLISHED'
                AND
                article.enabled = true
            ORDER BY
                ${field}
            DESC
        """;
    }

    public String getArticlesById(@Param("id") Long id, @Param("field") String field) {
        return """
            SELECT
                article.*
            FROM
                article
            JOIN
                article_tag
            ON
                article.id = article_tag.article_id
            WHERE
                tag.tag_id = #{id}
                AND
                article.is_public = true
                AND
                article.status = 'PUBLISHED'
                AND
                article.enabled = true
            ORDER BY
                ${field}
            DESC
        """;
    }

}
