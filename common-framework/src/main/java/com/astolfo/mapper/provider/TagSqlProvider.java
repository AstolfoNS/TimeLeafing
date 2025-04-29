package com.astolfo.mapper.provider;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

public class TagSqlProvider {

    public String getArticleByTagName(@Param("tagName") String tagName, @Param("field") String field) {
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
                article.status = 'ARTICLE'
            ORDER BY
                ${field}
            DESC
        """;
    }
}
