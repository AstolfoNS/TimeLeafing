package com.astolfo.mapper.provider;

import org.apache.ibatis.annotations.Param;

public class UserSqlProvider {

    public String getUserById(@Param("id") Long id) {
        return """
            SELECT
                user.*
            FROM
                user
            WHERE
                user.id = #{id}
        """;
    }

    public String getUserByUsername(@Param("username") String username) {
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
