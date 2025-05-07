package com.astolfo.mapper;

import com.astolfo.mapper.provider.TagSqlProvider;
import com.astolfo.model.entity.Tag;
import com.astolfo.model.vo.ArticleDetailsVO;
import com.astolfo.model.vo.ArticleSummaryVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.*;

@Mapper
public interface TagMapper extends BaseMapper<Tag> {

    @SelectProvider(type = TagSqlProvider.class, method = "getArticlesByTagName")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(
                    property = "author",
                    column = "author_id",
                    one = @One(select = "com.astolfo.mapper.UserMapper.getUserVOById")
            ),
            @Result(
                    property = "tagVOs",
                    column = "id",
                    many = @Many(select = "com.astolfo.mapper.ArticleMapper.getTagVOsById")
            )
    })
    Page<ArticleSummaryVO> getArticleSummaryVOsByTagName(
            Page<ArticleSummaryVO> page,
            @Param("field") String field,
            @Param("tagName") String tagName
    );

    @SelectProvider(type = TagSqlProvider.class, method = "getArticlesByTagName")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(
                    property = "author",
                    column = "author_id",
                    one = @One(select = "com.astolfo.mapper.UserMapper.getUserVOById")
            ),
            @Result(
                    property = "tagVOs",
                    column = "id",
                    many = @Many(select = "com.astolfo.mapper.ArticleMapper.getTagVOsById")
            )
    })
    Page<ArticleDetailsVO> getArticleDetailsVOsByTagName(
            Page<ArticleDetailsVO> page,
            @Param("field") String field,
            @Param("tagName") String tagName
    );

    @SelectProvider(type = TagSqlProvider.class, method = "getArticlesById")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(
                    property = "author",
                    column = "author_id",
                    one = @One(select = "com.astolfo.mapper.UserMapper.getUserVOById")
            ),
            @Result(
                    property = "tagVOs",
                    column = "id",
                    many = @Many(select = "com.astolfo.mapper.ArticleMapper.getTagVOsById")
            )
    })
    Page<ArticleSummaryVO> getArticleSummaryVOsById(
            Page<ArticleSummaryVO> page,
            @Param("field") String field,
            @Param("id") String id
    );

    @SelectProvider(type = TagSqlProvider.class, method = "getArticlesById")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(
                    property = "author",
                    column = "author_id",
                    one = @One(select = "com.astolfo.mapper.UserMapper.getUserVOById")
            ),
            @Result(
                    property = "tagVOs",
                    column = "id",
                    many = @Many(select = "com.astolfo.mapper.ArticleMapper.getTagVOsById")
            )
    })
    Page<ArticleDetailsVO> getArticleDetailsVOsById(
            Page<ArticleDetailsVO> page,
            @Param("field") String field,
            @Param("id") String id
    );
}
