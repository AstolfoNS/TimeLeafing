package com.astolfo.v1.mapper.provider;

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

    public String getRoleVOsById(@Param("id") Long id) {
        return """
            SELECT
                role.*
            FROM
                role
            JOIN
                user_role
            ON
                role.id = user_role.role_id
            WHERE
                user_role.user_id = #{id}
        """;
    }

}
