package com.astolfo.infrastructure.persistence.mapper.provider;

import org.apache.ibatis.annotations.Param;

public class UserSqlProvider {

    public String findById(@Param("id") Long id) {
        return """
            SELECT
                user.*
            FROM
                user
            WHERE
                user.id = #{id}
        """;
    }

    public String findByUsername(@Param("username") String username) {
        return """
            SELECT
                user.*
            FROM
                user
            WHERE
                user.username = #{username}
        """;
    }
}
