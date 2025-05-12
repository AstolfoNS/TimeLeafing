package com.astolfo.infrastructure.persistence.mapper.provider;

import org.apache.ibatis.annotations.Param;

public class UserSqlProvider {

    public String findUserEntityById(@Param("id") Long id) {
        return """
            SELECT
                user.*
            FROM
                user
            WHERE
                user.id = #{id}
        """;
    }

    public String findUserEntityByUsername(@Param("username") String username) {
        return """
            SELECT
                user.*
            FROM
                user
            WHERE
                user.username = #{username}
        """;
    }

    public String findUserEntityByEmail(@Param("emailAddress") String emailAddress) {
        return """
            SELECT
                user.*
            FROM
                user
            WHERE
                user.email = #{emailAddress}
        """;
    }
}
